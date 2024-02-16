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

    // Méthode statsUpdate
    public void statsUpdate() { // La fonction fight() de la classe héro appel cette fonction si le héro bat le monstre
        this.statsReset(); // Si on améliore les stats d'un ennemi alors on reset ses stats indirectement avant ça
        
        this.health += healthLevelUp;
        this.attackPower += attackPowerLevelUp;
        this.experience += experienceLevelUp;
        setInitialHealth(this.health); // Les points de vie initiaux de l'ennemi augmente également (autrement il y a reset à chaque combat)
        setInitialAttackPower(this.attackPower); // Même chose pour les points d'attaque initiaux
    }

    // Méthode decreaseHealth
    public int decreaseHealth(int damageTaken) { // Appelé lorsqu'il y a combat
        this.health = this.health - damageTaken;
        return this.health;
    }
}
