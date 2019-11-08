package com.hazebyte.base.demo;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.Size;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class StateChangePage extends Base implements Listener {

    /**
     * Creates a inventory that lists players
     */
    public StateChangePage(JavaPlugin plugin, String title, Size size) {
        super(plugin, title, size);

        Button xpButton = new Button(new ItemBuilder(Material.DIAMOND)
                .displayName("%player")
                .lore("XP: %xp", "Health: %health", "Msg: %msg")
                .asItemStack());
        this.attach(xpButton);
        this.addIcon(xpButton);
    }

    @Override
    public void update(HumanEntity entity) {
        if (!(entity instanceof Player)) return;

        Player player = (Player) entity;
        this.setState(entity, "player", player.getDisplayName());
        this.setState(entity, "xp", player.getExp());
        this.setState(entity, "health", player.getHealth());
        this.setState(entity, "msg", "random msg test");
    }
}
