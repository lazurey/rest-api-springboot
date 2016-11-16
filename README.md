# Implement REST APIs with springboot

## Intro

This skeleton project is a Todo application backend. It contains all libraries
you need to implement it.

## Requirements

#### API Implementation

Implement all APIs described in API document here: **todo-list/docs/todo-api.raml**.

#### 100% Test Coverage Of Your Implementation

This project has been configured **jacoco** to generate test report, and you could find the report under **todo-list/build/jacocoHtml/index.html**.  Please find more info about this gradle plugin: https://docs.gradle.org/current/userguide/jacoco_plugin.html.

## Something probably you need to know

#### RAML

RAML represents RESTful API Modeling Language (RAML). It's a very easy and readable way to desgin and describe the API. If you've never heard of it, please go to http://raml.org to find more information.

#### Database support

There is no database support in the project, so in order to manage all todo items, you might need to implement a very simple database, or you could apply **H2** or any other lightweight/in-memory database as you like.

For example, you could use **ArrayList** to manage all your items, it's OK.
