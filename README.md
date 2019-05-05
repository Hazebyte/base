# base

base is a bukkit/spigot library to build interfaces. It currently has support for paginated inventories with eventful buttons.

* All interfaces are components that manage their own state
* Build components that are OOP and encapsulated
* Create a highly interactive experience for end users
* Designed with **pagination** in mind

![https://i.imgur.com/2jBL9Xh.gif](https://i.imgur.com/2jBL9Xh.gif)

## Getting Started

Add Base to your project repository. You may do this using the project jar or using Gradle & Maven (todo)

Building the jar
```
git clone https://github.com/Hazebyte/Base.git base
cd base
make
cp /build/lib/base-0.0.0.jar my-folder
```

**[Example Project](https://github.com/Hazebyte/base/blob/master/src/main/java/com/hazebyte/example/)**

**Source to create the inventory above**
```
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
```

## Contribution

This project is a work in progress. There are still many places to be perfected. 
Any suggestions or contributions are welcomed!

## Reference (todo)

JavaDoc
