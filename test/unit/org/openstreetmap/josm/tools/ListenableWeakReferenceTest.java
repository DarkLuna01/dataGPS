// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.tools;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.Test;
import org.openstreetmap.josm.testutils.JOSMTestRules;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Tests for {@link ListenableWeakReference}
 * @author Michael Zangl
 * @since 12181
 */
class ListenableWeakReferenceTest {
    /**
     * Default test rules.
     */
    @RegisterExtension
    @SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public JOSMTestRules test = new JOSMTestRules();
    private Object object;
    private boolean called;

    /**
     * Tests that {@link ListenableWeakReference#onDereference()} is called.
     * @throws InterruptedException never
     */
    @Test
    void testOnDereference() throws InterruptedException {
        object = new Object();
        called = false;
        ListenableWeakReference<Object> weak = new ListenableWeakReference<>(object, () -> called = true);
        assertFalse(called);
        assertSame(object, weak.get());

        // now delete it
        object = null;
        System.gc();
        System.runFinalization();
        // now we wait for the listener thread
        Thread.sleep(200);
        assertTrue(called);

        assertNotNull(weak);
        assertNull(weak.get());
    }

}
