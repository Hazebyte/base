# base

base is a bukkit/spigot library to build interfaces. It currently has support for paginated inventories with eventful buttons.

* All interfaces are components that manage their own state
* Build components that are OOP and encapsulated
* Create a highly interactive experience for end users
* Designed with pagination in mind

## Basic usage

```

```

### Advanced usage

Create a new menu page

```
public class ItemPage extends Base {

    public ItemPage(JavaPlugin plugin, String title) {
        super(plugin, title, Size.from(27));

        for (int i = 0; i < 36; i++) {
            // Adding a new button to the menu
            this.addIcon(new Button(new ItemStack(Material.DIAMOND)));
        }
    }
}
```

Create a new button

```

```
