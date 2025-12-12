<?php
/**
 * PHPUnit bootstrap file - creates compatibility alias for old test class naming
 */

require_once __DIR__ . '/vendor/autoload.php';

// Create alias for older PHPUnit_Framework_TestCase naming convention
if (!class_exists('PHPUnit_Framework_TestCase')) {
    class_alias('PHPUnit\Framework\TestCase', 'PHPUnit_Framework_TestCase');
}
