public class HeroEquilibre extends Hero {
    
    //--------------------// Constructeur //--------------------// 
    public HeroEquilibre(int health, int attackPower) {
        super(health, attackPower);
    }

    //--------------------// Autres méthodes //--------------------//
    // Méthode decreaseHealth
    @Override
    public int decreaseHealth(int damageTaken) {
        this.health = getHealth() - damageTaken;
        return health;
    }

    // Méthode statsUpdate
    @Override
    public void statsUpdate() {
        this.attackPower += attackerPowerLevelUp;
        this.maxHealth += maxHealthLevelUp; // Doit être en premier (avant que l'on réinitialise la vie du héro)
        this.health = maxHealth;
    }
}
