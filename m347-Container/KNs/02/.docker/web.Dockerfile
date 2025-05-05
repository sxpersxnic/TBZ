FROM php:8.3.20-apache

WORKDIR /var/www/html

RUN docker-php-ext-install mysqli

COPY php .

EXPOSE 80