public class Enemy {

    //--------------------// Attributs privés //--------------------//
    private int initialHealth;
    private int initialAttackPower;
    private int health = 100;
    private int attackPower = 25;
    private int experience = 35;

    //--------------------// Constantes //--------------------//
    private final int healthLevelUp = 10;
    private final int attackPowerLevelUp = 5;
    private final int experienceLevelUp = 8;

    //--------------------// Constructeur //--------------------//
    public Enemy() {
        this.initialHealth = health; // Initialisation initialHealth
        this.initialAttackPower = attackPower; // Initialisation initialAttackPower
    }

    //--------------------// Getters //--------------------//
    public int getHealth() { return this.health; }

    public int getPointsAttaque() { return this.attackPower; }

    public int getExperience() { return this.experience; }

    public int getAttackPower() { return this.attackPower; }

    //--------------------// Setters //--------------------//
    private void setInitialHealth(int initialHealth) { this.initialHealth = initialHealth; }

    private void setInitialAttackPower(int attackPower) { this.initialAttackPower = attackPower; }

    //--------------------// Autres méthodes //--------------------//
    // Méthode statsReset
    private void statsReset() {
        this.health = this.initialHealth;
        this.attackPower = this.initialAttackPower;
    }

    // Méthode updateStatsBasedOnDefeats
    public void updateStatsBasedOnDefeats(int numberOfDefeatedEnemies) {
        // Les stats de l'ennemi sont reset
        this.statsReset();

        // On augmente les stats de l'ennemi par rapport au nombre d'ennemis battus
        this.health += (this.healthLevelUp * numberOfDefeatedEnemies);
        this.attackPower += (this.attackPowerLevelUp * numberOfDefeatedEnemies);
        this.experience += (this.experienceLevelUp * numberOfDefeatedEnemies);

        // On modifie les stats initiaux
        setInitialHealth(this.health);
        setInitialAttackPower(this.attackPower);
    }

    // Méthode decreaseHealth
    public int decreaseHealth(int damageTaken) { // Appelé lorsqu'il y a combat
        this.health = this.health - damageTaken;
        return this.health;
    }
}
