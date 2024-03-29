// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.gui.dialogs.relation.actions;

import static org.openstreetmap.josm.tools.I18n.tr;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import org.openstreetmap.josm.tools.ImageProvider;
import org.openstreetmap.josm.tools.Shortcut;

/**
 * Move the currently selected members up.
 * @since 9496
 */
public class MoveUpAction extends AbstractRelationEditorAction {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code MoveUpAction}.
     * @param editorAccess An interface to access the relation editor contents.
     * @param actionMapKey action map key
     */
    public MoveUpAction(IRelationEditorActionAccess editorAccess, String actionMapKey) {
        super(editorAccess, actionMapKey, IRelationEditorUpdateOn.MEMBER_TABLE_SELECTION);
        new ImageProvider("dialogs", "up").getResource().attachImageIcon(this, true);
        Shortcut sc = Shortcut.registerShortcut("relationeditor:moveup", tr("Relation Editor: Move Up"), KeyEvent.VK_UP, Shortcut.ALT);
        sc.setAccelerator(this);
        sc.setTooltip(this, tr("Move the currently selected members up"));
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editorAccess.getMemberTableModel().moveUp(editorAccess.getMemberTable().getSelectedRows());
        editorAccess.stopMemberCellEditing();
    }

    @Override
    protected void updateEnabledState() {
        setEnabled(editorAccess.getMemberTableModel().canMoveUp(editorAccess.getMemberTable().getSelectedRows()));
    }
}
