package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Administrator;
import com.fwproblemsolversite.enums.AccountType;

public class AdministratorTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // test constructor with valid data
    @Test
    public void testConstructor() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        assertNotNull(admin);
    }

    // test account type is ADMIN
    @Test
    public void testGetAccountType() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        assertEquals(AccountType.ADMIN, admin.getAccountType());
    }

    // test ban method (returns false for now)
    @Test
    public void testBan() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        boolean result = admin.ban(UUID.randomUUID());

        assertFalse(result);
    }

    // test mute method (currently returns false)
    @Test
    public void testMute() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        boolean result = admin.mute(UUID.randomUUID());

        assertFalse(result);
    }

    // test terminate method (currently returns false)
    @Test
    public void testTerminate() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        boolean result = admin.terminate(UUID.randomUUID());

        assertFalse(result);
    }

    // test seeReport does not crash the system
    @Test
    public void testSeeReport() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        admin.seeReport(UUID.randomUUID());

        assertTrue(true);
    }

    // test deleteReport does not crash the system
    @Test
    public void testDeleteReport() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        admin.deleteReport(UUID.randomUUID());

        assertTrue(true);
    }

    // test seeAllReports does not crash the system
    @Test
    public void testSeeAllReports() {
        Administrator admin = new Administrator(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        admin.seeAllReports();

        assertTrue(true);
    }
}