package com.example.espresso_tutorial;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.util.ArrayList;

public class HamcrestLibrary {

    @Test
    public void list_hasValue() {
            ArrayList<String> list = new ArrayList<String>();
            list.add("John");
            assertThat("Is list has John?", list, hasItem("John"));
            }
}
//    Logical based matchers
//
//        allOf − accept any number of matchers and matches only if all matchers are succeeded.
//
//        anyOf − accept any number of matchers and matches if any one matcher succeeded.
//
//        not − accept one matcher and matches only if the matcher failed and vice versa.
//
//        Text based matchers
//
//        equalToIgnoringCase − used to test whether the actual input equals the expected string ignoring case.
//
//        equalToIgnoringWhiteSpace − used to test whether the actual input equals the specified string ignoring case and white spaces.
//
//        containsString − used to test whether the actual input contains specified string.
//
//        endsWith − used to test whether the actual input starts with specified string.
//
//        startsWith − used to test whether actual the input ends with specified string.
//
//        Number based matchers
//
//        closeTo − used to test whether the actual input is close to the expected number.
//
//        greaterThan − used to test whether the actual input is greater than the expected number.
//
//        greaterThanOrEqualTo − used to test whether the actual input is greater than or equal to the expected number.
//
//        lessThan − used to test whether the actual input is less than the expected number.
//
//        lessThanOrEqualTo − used to test whether the actual input is less than or equal to the expected number.
//
//        Object based matchers
//
//        equalTo − used to test whether the actual input is equals to the expected object
//
//        hasToString − used to test whether the actual input has toString method.
//
//        instanceOf − used to test whether the actual input is the instance of expected class.
//
//        isCompatibleType − used to test whether the actual input is compatible with the expected type.
//
//        notNullValue − used to test whether the actual input is not null.
//
//        sameInstance − used to test whether the actual input and expected are of same instance.
//
//        hasProperty − used to test whether the actual input has the expected property
