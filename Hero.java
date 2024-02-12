import java.lang.Math;

public  class Hero {
    // Attributs privés
    private int health, maxHealth;
    private int attackPower;
    private int level = 1;
    private int experience = 0;
    

    // Constructeur
    public Hero(int health, int maxHealth, int attackPower) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackPower = attackPower;
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
        return this.miseNiveau(level);
    }

    public int getExperience() {
        return this.experience;
    }


    // Setters


    // Autres méthodes
    private int miseNiveau(int level) {
        if (experience >= pointExperience(level)) {
            this.experience = experience - pointExperience(level); // ***Le hero peut avoir plus que le max de pointExperience()?***
            this.level = level + 1;
        }
        return this.level;
    }

    private int pointExperience(int level) {
        int exp_requis = (int)((50 + (this.level + 1) * 20 * Math.pow(1.1, this.level + 1)) + 1); // Pour prendre l'entier supérieur, on ajoute 1 au résultat
                                                                                                    // et on cast un int sur cette addition (cela va tronquer)
        return exp_requis;
    }

    public int decreaseHealth(int enemyAttackPower) {
        this.health = health - enemyAttackPower;
        return health;
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
    */
}
