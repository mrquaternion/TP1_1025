import java.lang.Math;

public  class Hero {
    // Attributs privés
    protected int health;
    private int maxHealth;
    private int attackPower;
    private int initialHealth;
    private int level = 1;
    private int experience = 0;
    

    // Constructeur
    public Hero(int health, int maxHealth, int attackPower) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackPower = attackPower;
        this.initialHealth = health; // Initialisation initialHealth
    }

    // Getters
    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public int getLevel() {
        return this.levelUp(level);
    }

    public int getExperience() {
        return this.experience;
    }


    // Setters


    // Autres méthodes
    private int levelUp(int level) {
        if (experience >= experiencePoints(level)) {
            this.experience = experience - experiencePoints(level); // ***Le hero peut avoir plus que le max de pointExperience()?***
            this.level = level + 1;
        }
        return this.level;
    }

    private int experiencePoints(int level) {
        int required_exp = (int)((50 + (this.level + 1) * 20 * Math.pow(1.1, this.level + 1)) + 1); // Pour prendre l'entier supérieur, on ajoute 1 au résultat
                                                                                                    // et on cast un int sur cette addition (cela va tronquer)
        return required_exp;
    }

    public int decreaseHealth(int enemyAttackPower) {
        this.health = health - enemyAttackPower;
        return health;
    }

    // -----------------Méthode statsUpdate()-----------------
    public void statsUpdate() {
        this.attackPower = attackPower + 5;
        this.maxHealth = maxHealth + 12;
        this.health = initialHealth;
    }

    // Méthodes action
    // -----------------Méthode figthing()-----------------
    public String fighting(Enemy enemy) {
        int k = 1;
        while (this.getHealth() > 0 && enemy.getHealth() > 0) {
            this.decreaseHealth(enemy.getAttackPower());
            enemy.decreaseHealth(this.getAttackPower());

            System.out.println("   Fight " + k); // TEST
            System.out.println("      Enemy's health: " + enemy.getHealth()); // TEST
            System.out.println("      Hero's health: " + this.getHealth()); // TEST
            k++;
        }

        if (this.getHealth() <= 0) { // On regarde si le héro est mort après le combat
            return "dead"; 
        } else {
            enemy.statsUpdate(); // On update les stats du prochain ennemi (même s'il y en a pas)
            return "alive";
        }
    }

    // -----------------Méthode resting()-----------------
    public String resting() {
        return healing();
    }

    // -----------------Méthode healing()-----------------
    public String healing() {
        this.health = maxHealth;
        return Integer.toString(this.health);
    }

    // -----------------Méthode training()-----------------
    public String training() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
}
