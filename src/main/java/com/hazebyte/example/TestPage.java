package com.hazebyte.example;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.Size;
import com.hazebyte.base.foundation.CloseButton;
import com.hazebyte.base.foundation.NextButton;
import com.hazebyte.base.foundation.PreviousButton;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class TestPage extends Base {

    private int counter = 0;

    public TestPage(JavaPlugin plugin, String title) {
        super(plugin, title, Size.from(27));

        // Create a new button icon
        Button counter = new Button(new ItemBuilder(Material.CAULDRON)
                .displayName("%count")
                .asItemStack());
        // Attach a state change listener to the button
        this.attach(counter);

        // Add the button to four different pages in different slots
        this.setIcon(0, 0, counter);
        this.setIcon(1, 10, counter);
        this.setIcon(2, 15, counter);
        this.setIcon(3, 3, counter);

        int[] navPages = {0, 1, 2, 3};
        this.setIcon(navPages, 21, new PreviousButton());
        this.setIcon(navPages, 22, new CloseButton());
        this.setIcon(navPages, 23, new NextButton());
    }

    public void update(HumanEntity entity) {
        counter = (counter % 64) + 1;
        this.setState(entity, "count", "Counter " + counter);
        this.setState(entity, "%amount", counter);
        this.setState(entity, "%lore", Arrays.asList("Lore: " + counter));
    }
}
