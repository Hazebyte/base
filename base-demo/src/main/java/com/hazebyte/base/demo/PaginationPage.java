package com.hazebyte.base.demo;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.Size;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class PaginationPage extends Base {
    public PaginationPage(JavaPlugin plugin, String title, Size size) {
        super(plugin, title, size);

        for (int i = 0; i < 100; i++) {
            Button button = new Button(new ItemBuilder(Material.DIAMOND, (i % 64) + 1).asItemStack());
            this.addIcon(button);
        }
    }
}
