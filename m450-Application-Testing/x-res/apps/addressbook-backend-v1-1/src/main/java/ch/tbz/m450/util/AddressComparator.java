package ch.tbz.m450.util;

import java.util.Comparator;

import ch.tbz.m450.repository.Address;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        // First compare by lastname
        int lastNameComparison = a1.getLastname().compareToIgnoreCase(a2.getLastname());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        // If lastnames are equal, compare by firstname
        int firstNameComparison = a1.getFirstname().compareToIgnoreCase(a2.getFirstname());
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        // If firstnames are equal, compare by phonenumber
        int phoneComparison = a1.getPhonenumber().compareTo(a2.getPhonenumber());
        if (phoneComparison != 0) {
            return phoneComparison;
        }

        // If phonenumbers are equal, compare by registrationDate
        if (a1.getRegistrationDate() == null && a2.getRegistrationDate() == null) {
            return 0;
        }
        if (a1.getRegistrationDate() == null) {
            return -1;
        }
        if (a2.getRegistrationDate() == null) {
            return 1;
        }
        return a1.getRegistrationDate().compareTo(a2.getRegistrationDate());
    }

}
