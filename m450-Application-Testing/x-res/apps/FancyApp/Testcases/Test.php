<?php
/**
 * Created by PhpStorm.
 * User: s.kemper
 * Date: 29.03.2022
 * Time: 22:01
 */

require_once('../Application/MyFancyClass.php');

// for newer PHPUnit uncomment this line
//use PHPUnit\Framework\TestCase;

class Test extends PHPUnit_Framework_TestCase // for newer PHPUnit: TestCase; for older PHPUnit: PHPUnit_Framework_TestCase
{
    private $_myFancyClass;

    public function setUp() : void {
        // reset myFancyClass object
        unset($this->_myFancyClass);
        $this->_myFancyClass = new MyFancyClass();
    }

    public function testShortString() {
        // define test data
        $textEmpty = '';
        $text35Char = 'Ich bin Text mit 35 Char Länge (35)';
        $text130Char = 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam';
        $text591Char = 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.';

        $endingEmpty = '';
        $endingShort = '$';
        $endingLong = 'I am a end and a complete sentence.';

        // test the method
        $this->assertEquals('', $this->_myFancyClass->shortString($textEmpty, 100));
        $this->assertEquals($text35Char, $this->_myFancyClass->shortString($text35Char, 100));
        $this->assertEquals('Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt u...', $this->_myFancyClass->shortString($text130Char, 100));
        $this->assertEquals($this->_myFancyClass->shortString($text130Char, 100), $this->_myFancyClass->shortString($text591Char, 100));
        $this->assertNotEquals($this->_myFancyClass->shortString($text130Char, 200), $this->_myFancyClass->shortString($text591Char, 200));

        $this->assertFalse($this->_myFancyClass->shortString($textEmpty, 20, $endingLong));
        $this->assertFalse($this->_myFancyClass->shortString($text35Char, 20, $endingLong));

        $this->assertStringEndsWith($endingShort, $this->_myFancyClass->shortString($text130Char, 129, $endingShort));
        $this->assertStringEndsWith('a', $this->_myFancyClass->shortString($text130Char, 129, $endingEmpty));
        $this->assertStringEndsWith('...', $this->_myFancyClass->shortString($text591Char, 100));

        $this->assertEquals($text35Char, $this->_myFancyClass->shortString($text35Char, 35, $endingLong));
        $this->assertEquals($text130Char, $this->_myFancyClass->shortString($text130Char, 130, $endingShort));
    }

    public function testCalcAverage() {
        // define test data
        $emptyValues = [];
        $average5 = [4.5,5.0,5.5];
        $valuesString = ['4.5','5.0','5.5'];
        $valuesWrong = ['3.44','bestanden','nicht bewertbar'];

        // test the method
        $this->assertEquals(0, $this->_myFancyClass->calcAverage($emptyValues));
        $this->assertEquals(5, $this->_myFancyClass->calcAverage($average5));
        $this->assertEquals($this->_myFancyClass->calcAverage($average5), $this->_myFancyClass->calcAverage($valuesString));
        $this->assertFalse($this->_myFancyClass->calcAverage($valuesWrong));
    }

    public function testGetOpposite() {
        // define test data
        $average5 = [4.5,5.0,5.5];
        $valuesString = ['4.5','5.0','5.5'];

        $delimiter1 = ', ';
        $delimiter2 = '?Hallo?';
        $delimiter3 = ' ';
        $delimiter4 = ',';

        $stringList = 'Apfel,Birne,Pflaume,Pfirsich';
        $stringListSpace = 'Apfel, Birne, Pflaume, Pfirsich';
        $stringLoremIpsum = 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non,umy eirmod tempor invidunt ut.';


        // test the method
        $this->assertEquals('4.5,5,5.5', $this->_myFancyClass->getOpposite($average5));
        $this->assertEquals('4.5,5.0,5.5', $this->_myFancyClass->getOpposite($valuesString));
        $this->assertNotEquals($this->_myFancyClass->getOpposite($average5, $delimiter1), $this->_myFancyClass->getOpposite($valuesString, $delimiter2));

        $this->assertEquals(15, count($this->_myFancyClass->getOpposite($stringLoremIpsum, $delimiter3)));
        $this->assertEquals(3, count($this->_myFancyClass->getOpposite($stringLoremIpsum, $delimiter1)));
        $this->assertEquals(4, count($this->_myFancyClass->getOpposite($stringLoremIpsum, $delimiter4)));

        $this->assertEquals($this->_myFancyClass->getOpposite($stringList), $this->_myFancyClass->getOpposite($stringList,$delimiter4));
        $this->assertEquals($this->_myFancyClass->getOpposite($this->_myFancyClass->getOpposite($stringList)), $stringList);
        $this->assertEquals($this->_myFancyClass->getOpposite($this->_myFancyClass->getOpposite($stringListSpace)), $stringListSpace);
        $this->assertEquals($this->_myFancyClass->getOpposite($this->_myFancyClass->getOpposite($stringListSpace, $delimiter1),$delimiter4), $stringList);

        $this->assertFalse($this->_myFancyClass->getOpposite($this->_myFancyClass));
    }
}
