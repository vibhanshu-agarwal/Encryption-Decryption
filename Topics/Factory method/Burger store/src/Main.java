class TestDrive {
    public static void main(String[] args) throws InterruptedException {
        /* write your code here */
        BurgerStore burgerStore = new BurgerStore();
        Burger burger = burgerStore.orderBurger("Chinese");
        burger = burgerStore.orderBurger("American");
        burger = burgerStore.orderBurger("Russian");
    }
}

abstract class BurgerFactory {

    abstract Burger createBurger(String type);

    Burger orderBurger(String type) throws InterruptedException {
        Burger burger = createBurger(type);
        if (burger == null) {
            System.out.println("Sorry, we are unable to create this kind of burger\n");
            return null;
        }
        System.out.println("Making a " + burger.getName() + " Burger");
        burger.putBun();/* write your code here */
        burger.putCutlet();/* write your code here */
        burger.putSauce();/* write your code here */
        Thread.sleep(1500L);
        System.out.println(burger.getName() + " Burger ready" + "\n");
        return burger;
    }
}

class BurgerStore extends BurgerFactory {
    @Override
    Burger createBurger(String type) {
        return switch (type) {
            case "Chinese" -> new ChineseBurger(type); /* write your code here */
            case "American" -> new AmericanBurger(type);/* write your code here */
            case "Russian" -> new RussianBurger(type);/* write your code here */
            default -> null;
        };

    }
}

abstract class Burger {
    private String name;

    Burger(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void putBun() {
        System.out.println("Putting bun");
    }

    void putCutlet() {
        System.out.println("Putting patty");
    }

    void putSauce() {
        System.out.println("Putting sauce");
    }

}

class ChineseBurger extends Burger {
    ChineseBurger(String name) {
        super(name);
    }
}

class AmericanBurger extends Burger {
    AmericanBurger(String name) {
        super(name);
    }
}

class RussianBurger extends Burger {
    RussianBurger(String name) {
        super(name);
    }
}