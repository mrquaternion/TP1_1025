public class Enemy {

    // Attributs privés
    private int health = 10;
    private int attackPower = 25;
    private int experience = 35;
    private int initialHealth;
    private int initialAttackPower;

    // Constructeur
    public Enemy() {
        this.initialHealth = health; // Initialisation initialHealth
        this.initialAttackPower = attackPower; // Initialisation initialAttackPower
    }

    // Getters
    public int getHealth() {
        return this.health;
    }

    public int getPointsAttaque() {
        return this.attackPower;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    
    // Setters



    // Autres méthodes
    // -----------------Méthode statsReset()-----------------
    private void statsReset() {
        this.health = initialHealth;
        this.attackPower = initialAttackPower;
    }

    // -----------------Méthode statsUpdate()-----------------
    public void statsUpdate() { // La fonction fight() de la classe héro appel cette fonction si le héro bat le monstre
        this.statsReset(); // Si on améliore les stats d'un ennemi alors on reset ses stats indirectement avant ça
        this.health = health + 10;
        this.attackPower = attackPower + 5;
        this.experience = experience + 8;
    }

    // -----------------Méthode heroAttackPower()-----------------
    public int decreaseHealth(int heroAttackPower) {
        this.health = health - heroAttackPower;
        return health;
    }
}
