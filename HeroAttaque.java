public class HeroAttaque extends Hero {

    //--------------------// Constructeur //--------------------// 
    public HeroAttaque(int health, int attackPower) {
        super(health, attackPower * 2); // Les points d'attaque doublent
    }

    //--------------------// Autres méthodes //--------------------// 
    // Méthode decreaseHealth
    @Override
    public int decreaseHealth(int enemyAttackPower) {
        this.health -= (enemyAttackPower * 2); // Le héro se prend 2 fois les points d'attaque de l'ennemi
        return health;
    }

    // Méthode statsUpdate
    @Override
    public void statsUpdate() {
        this.maxHealth += maxHealthLevelUp;
        this.health = maxHealth;
        this.attackPower += (attackerPowerLevelUp * 2); // On multiplie par deux les points d'attaque
    }

    // Méthode training
    @Override
    public void training(int attackTrainingBonus) { this.attackPower += (attackTrainingBonus * 2); }
}
