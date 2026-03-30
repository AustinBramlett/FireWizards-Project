package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Contributor;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;

public class ContributorTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // helper to create contributor
    private Contributor createContributor() {
        return new Contributor(
            UUID.randomUUID(),
            "Alex",
            "Smithson",
            "asmithson",
            "asmithson@email.com",
            "password123",
            new ArrayList<>()
        );
    }

    // test constructor with valid data
    @Test
    public void testConstructor() {
        Contributor contributor = createContributor();
        assertNotNull(contributor);
    }

    // test account type is CONTRIBUTOR
    @Test
    public void testGetAccountType() {
        Contributor contributor = createContributor();
        assertEquals(AccountType.CONTRIBUTOR, contributor.getAccountType());
    }

    // test addProblem (currently returns false)
    @Test
    public void testAddProblem() {
        Contributor contributor = createContributor();

        boolean result = contributor.addProblem(
            "Title",
            "Description",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            "Answer"
        );

        assertFalse(result);
    }

    // test removeProblem (currently returns false)
    @Test
    public void testRemoveProblem() {
        Contributor contributor = createContributor();

        boolean result = contributor.removeProblem(UUID.randomUUID());

        assertFalse(result);
    }
    // test addProblem with null values
    @Test
public void testAddProblemNullValues() {
    Contributor contributor = createContributor();

    boolean result = contributor.addProblem(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        0.0,
        null
    );

    assertFalse(result);
}

    @Test   
public void testRemoveProblemNull() {
    Contributor contributor = createContributor();

    boolean result = contributor.removeProblem(null);

    assertFalse(result);
}

    @Test // test multiple calls to addProblem to make sure it consistently returns false
public void testMultipleAddProblemCalls() {
    Contributor contributor = createContributor();

    boolean result1 = contributor.addProblem(
        "T1", "D1", new ArrayList<>(), null,
        new ArrayList<>(), new ArrayList<>(),
        null, new ArrayList<>(), 1.0, "A1"
    );

    boolean result2 = contributor.addProblem(
        "T2", "D2", new ArrayList<>(), null,
        new ArrayList<>(), new ArrayList<>(),
        null, new ArrayList<>(), 2.0, "A2"
    );

    assertFalse(result1);
    assertFalse(result2);
}
}