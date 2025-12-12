<?php
/**
 * Created by PhpStorm.
 * User: s.kemper
 * Date: 29.03.2022
 * Time: 22:05
 *
 * This class does a lot of things... but has no productive purpose.
 * The only purpose of this class is to teach the basic functions
 * of PHP and how to use them.
 */

class MyFancyClass
{
    public function shortString($text, $length, $ending = '...') {
        // If ending is longer than length, return false (can't truncate properly)
        if (mb_strlen($ending) > $length) {
            return false;
        }
        
        // If text is empty, return empty string
        if ($text === '') {
            return '';
        }
        
        // If text length is less than or equal to limit, return original text
        if (mb_strlen($text) <= $length) {
            return $text;
        }
        
        // Truncate text and append ending
        $truncatedLength = $length - mb_strlen($ending);
        return mb_substr($text, 0, $truncatedLength) . $ending;
    }

    public function calcAverage($values) {
        // Return 0 for empty array
        if (empty($values)) {
            return 0;
        }
        
        $sum = 0;
        $count = count($values);
        
        foreach ($values as $value) {
            // Check if value is numeric
            if (!is_numeric($value)) {
                return false;
            }
            $sum += floatval($value);
        }
        
        return $sum / $count;
    }

    public function getOpposite($value, $delimiter = ',') {
        // If value is an array, convert to string (implode)
        if (is_array($value)) {
            return implode($delimiter, $value);
        }
        
        // If value is a string, convert to array (explode)
        if (is_string($value)) {
            return explode($delimiter, $value);
        }
        
        // For any other type, return false
        return false;
    }
}