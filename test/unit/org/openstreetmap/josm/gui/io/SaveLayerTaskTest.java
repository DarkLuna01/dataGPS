// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.gui.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openstreetmap.josm.data.osm.DataSet;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.testutils.JOSMTestRules;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Unit tests of {@link SaveLayerTask} class.
 */
class SaveLayerTaskTest {
    /**
     * Setup test.
     */
    @RegisterExtension
    @SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public JOSMTestRules test = new JOSMTestRules();

    /**
     * Test of {@link SaveLayerTask} class - null case.
     */
    @Test
    @SuppressFBWarnings(value = "NP_NULL_PARAM_DEREF_NONVIRTUAL")
    void testSaveLayerTaskNull() {
        assertThrows(IllegalArgumentException.class, () -> new SaveLayerTask(null, null));
    }

    /**
     * Test of {@link SaveLayerTask} class - nominal case.
     */
    @Test
    void testSaveLayerTaskNominal() {
        assertNotNull(new SaveLayerTask(new SaveLayerInfo(new OsmDataLayer(new DataSet(), "", null)), null));
    }
}
