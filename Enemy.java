public class Enemy {

    //--------------------// Attributs privés //--------------------//
    private int health = 100;
    private int attackPower = 25;
    private int experience = 35;

    //--------------------// Constantes //--------------------//
    private final int healthLevelUp = 10;
    private final int attackPowerLevelUp = 5;
    private final int experienceLevelUp = 8;

    //--------------------// Constructeur //--------------------//
    public Enemy() {}

    //--------------------// Getters //--------------------//
    public int getHealth() { return this.health; }

    public int getPointsAttaque() { return this.attackPower; }

    public int getExperience() { return this.experience; }

    public int getAttackPower() { return this.attackPower; }

    //--------------------// Setters //--------------------//
    

    //--------------------// Autres méthodes //--------------------//
    // Méthode updateStatsBasedOnDefeats
    public void updateStatsBasedOnDefeats(int numberOfDefeatedEnemies) {
        // On augmente les stats de l'ennemi par rapport au nombre d'ennemis battus
        this.health += (this.healthLevelUp * numberOfDefeatedEnemies);
        this.attackPower += (this.attackPowerLevelUp * numberOfDefeatedEnemies);
        this.experience += (this.experienceLevelUp * numberOfDefeatedEnemies);
    }

    // Méthode decreaseHealth
    public int decreaseHealth(int damageTaken) { // Appelé lorsqu'il y a combat
        this.health = this.health - damageTaken;
        return this.health;
    }
}
