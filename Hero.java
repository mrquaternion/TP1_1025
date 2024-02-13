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
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }


    // Setters


    // Autres méthodes
    public void levelUp() {
        if (experience >= experiencePoints(this.level)) { //on regarde si les héro peut level up
            this.experience = 0; // On remet les points d'xp du hero à 0
            this.level += 1; 
            statsUpdate(); // on augmente les stas du hero
        }
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
            this.experience += enemy.getExperience(); // On ajoute l'experience gagné suite à la victoire du hero
            return true;
        }
    }

    // -----------------Méthode resting()-----------------
    public void resting() { // 
        this.health = this.maxHealth; //remet les points de vie du hero au max
        System.out.println("test");
    }

    // -----------------Méthode healing()-----------------
    public void healing(int healingPoint) {
         if (this.health + healingPoint >= this.maxHealth) { //verifie que le soin ne depasse pas la limite maxHealth
            this.health = this.maxHealth; 
         }else{
            this.health += healingPoint;
         }
    }

    // -----------------Méthode training()-----------------
    public void training(int attackTrainingBonus) {
        this.attackPower += attackTrainingBonus;
    }
}
