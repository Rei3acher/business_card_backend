package businesscard.rest;


import java.time.LocalDate;


public record CusAssignment(
        String account_name,
        String firstName,
        String sureName,
        String title,
        String sex,
        String initials,
        String telephoneNumber,
        Integer birthYear,
        String department,
        String position,
        String clusterName,
        LocalDate seniority
) {}



