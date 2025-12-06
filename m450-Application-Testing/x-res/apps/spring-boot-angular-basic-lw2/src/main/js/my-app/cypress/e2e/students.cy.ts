/// <reference types="cypress" />

describe('Students E2E flow', () => {

  beforeEach(() => {
    cy.visit('/students');
  });

  it('should load initial students from backend and display them in table', () => {
    cy.get('table.table tbody tr')
      .should('have.length', 5);

    cy.get('table.table tbody tr').first()
      .within(() => {
        cy.get('td').eq(1).should('contain.text', 'Jonas');
        cy.get('td').eq(2).should('contain.text', 'jonas@tbz.ch');
      });

    cy.get('table.table tbody tr').eq(4)
      .within(() => {
        cy.get('td').eq(1).should('contain.text', 'Ann');
        cy.get('td').eq(2).should('contain.text', 'ann@tbz.ch');
      });
  });

  it('should allow adding a new student via add-students page and display it', () => {
    // Navigate to add student page
    cy.visit('/addstudents');

    cy.get('input#name').type('Charlie');
    cy.get('input#email').type('charlie@tbz.ch');

    cy.get('button[type="submit"]').click();

    cy.visit('/students');

    cy.get('table.table tbody tr')
      .should('have.length', 6);

    cy.get('table.table tbody tr').last()
      .within(() => {
        cy.get('td').eq(1).should('contain.text', 'Charlie');
        cy.get('td').eq(2).should('contain.text', 'charlie@tbz.ch');
      });
  });
});
