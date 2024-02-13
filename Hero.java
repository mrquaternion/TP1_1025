import java.lang.Math;

public  class Hero {
    // Attributs privés
    private int maxHealth;
    private int level = 1;
    private int experience = 0;
    public int numberOfEnemiesDefeated = 0;

    // Attributs protégés
    protected int health;
    protected int attackPower;
    protected final int attackerPowerLevelUp = 5;
    

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

    // -----------------Méthode decreaseHealth()-----------------
    public int decreaseHealth(int enemyAttackPower) { // Appelé lorsqu'il y a combat
        this.health = health - enemyAttackPower;
        return health;
    }

    // -----------------Méthode statsUpdate()-----------------
    public void statsUpdate() {
        this.attackPower = attackPower + 5;
        this.maxHealth = maxHealth + 12;
        this.health = maxHealth;
    }

    // Méthodes action
    // -----------------Méthode figthing()-----------------
    public Boolean fighting(Enemy enemy) {
        int k = 1; // TEST
        while (this.getHealth() > 0 && enemy.getHealth() > 0) {
            

            enemy.decreaseHealth(this.getAttackPower()); // hero attaque en premier
            if (enemy.getHealth() > 0) { // vérifie si l'enemie a toujours des points de vie pour attaquer
                this.decreaseHealth(enemy.getAttackPower());
            }

            System.out.println("   Fight " + k); // TEST
            System.out.println("      Enemy's health after combat: " + enemy.getHealth()); // TEST
            System.out.println("      Hero's health after combat: " + this.getHealth()); // TEST
            k++;
        }

        if (this.getHealth() <= 0) { // On regarde si le héro est mort après le combat
            return false; 
        } else {
            enemy.statsUpdate(); // On update les stats du prochain ennemi (même s'il y en a pas)
            return true;
        }
    }

    // -----------------Méthode resting()-----------------
    public void resting() {
        this.health = this.maxHealth;
        System.out.println("test");
    }

    // -----------------Méthode healing()-----------------
    public void healing(int healingPoint) {
        this.health += healingPoint;
    }

    // -----------------Méthode training()-----------------
    public void training(int attackTrainingBonus) {
        this.attackPower += attackTrainingBonus;
    }
}
