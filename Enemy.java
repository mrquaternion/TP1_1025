public class Enemy {

    //--------------------// Attributs privés //--------------------//
    private int health;
    private int attackPower;
    private int experience;

    //--------------------// Constantes //--------------------//
    private final int initialHealth = 100;
    private final int initialAttackPower = 25;
    private final int initialExperience = 35;
    private final int healthLevelUp = 10;
    private final int attackPowerLevelUp = 5;
    private final int experienceLevelUp = 8;

    //--------------------// Constructeur //--------------------//
    public Enemy() {
        this.health = initialHealth;
        this.attackPower = initialAttackPower;
        this.experience = initialExperience;
    }

    //--------------------// Getters //--------------------//
    public int getHealth() { return this.health; }

    public int getExperience() { return this.experience; }

    public int getAttackPower() { return this.attackPower; }

    //--------------------// Setters //--------------------//
    // Pas vraiment besoin parce que on ne modifie rien de l'ennemi en dehors de cette classe
  

    //--------------------// Autres méthodes //--------------------//
    // Méthode statsReset
    private void statsReset() {
        this.health = initialHealth;
        this.attackPower = initialAttackPower;
        this.experience = initialExperience;
    }

    // Méthode updateStatsBasedOnDefeats
    public void updateStatsBasedOnDefeats(int numberOfDefeatedEnemies) {
        // Les stats de l'ennemi sont reset
        this.statsReset();

        // On augmente les stats de l'ennemi par rapport au nombre d'ennemis battus
        this.health += (healthLevelUp * numberOfDefeatedEnemies);
        this.attackPower += (attackPowerLevelUp * numberOfDefeatedEnemies);
        this.experience += (experienceLevelUp * numberOfDefeatedEnemies);
    }

    // Méthode decreaseHealth
    public int decreaseHealth(int damageTaken) { // Appelé lorsqu'il y a combat
        this.health -= damageTaken;
        return health;
    }
}
