import java.lang.Math;

public  class Hero {

    //--------------------// Attributs privés //--------------------// 
    private int level = 1;
    private int experience = 0;
    public int numberOfEnemiesDefeated = 0;

    //--------------------// Attributs protégés //--------------------// 
    protected int health;
    protected int maxHealth;
    protected int attackPower;

    //--------------------// Constantes disponibles dans HeroAttaque, HeroDefense et HeroEquilibre //--------------------//
    protected final int maxHealthLevelUp = 12;
    protected final int attackerPowerLevelUp = 6;
    
    //--------------------// Constructeur //--------------------//
    public Hero(int health, int maxHealth, int attackPower) {
        setHealth(maxHealth);
        setMaxHealth(maxHealth);
        setAttackPower(attackPower);
    }

    //--------------------// Getters //--------------------//
    public int getHealth() { return this.health; }

    public int getMaxHealth() { return this.maxHealth; }

    public int getAttackPower() { return this.attackPower; }

    public int getLevel() { return this.level; }

    public int getExperience() { return this.experience; }

    //--------------------// Setters //--------------------//
    public void setLevel(int level) { this.level = level; }

    public void setExperience(int experience) { this.experience = experience; }

    public void setNumberOfEnemiesDefeated(int numberOfEnemiesDefeated) { this.numberOfEnemiesDefeated = numberOfEnemiesDefeated; }

    public void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        } else {
            throw new IllegalArgumentException("Les points de vie du héro doivent être supérieur à 0.");
        }
    }

    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; } // Les points de vie maximum du héro dépendent des points de vie alors
                                                                            // pas besoin de vérifier

    public void setAttackPower(int attackPower) {
        if (attackPower > 0) {
            this.attackPower = attackPower;
        } else {
            throw new IllegalArgumentException("Les points d'attaque du héro doivent être supérieur à 0.");
        }
    }

    //--------------------// Autres méthodes //--------------------//
    // Méthode levelUp
    public void levelUp() {
        if (experience >= experiencePoints(this.level)) { // On regarde si le héro peut level up
            this.experience = 0; // On remet les points d'xp du hero à 0
            this.level += 1; 
            this.statsUpdate(); // On augmente les stats du hero
        }
    }

    // Méthode statsUpdate
    public void statsUpdate() {
        setHealth(getMaxHealth());
        setMaxHealth(getMaxHealth() + maxHealthLevelUp);
        setAttackPower(getAttackPower() + attackerPowerLevelUp);
    }

    // Méthode experiencePoints
    private int experiencePoints(int level) {
        int required_exp = (int)((50 + (this.level + 1) * 20 * Math.pow(1.1, this.level + 1)) + 0.99999); // Pour prendre l'entier supérieur, on ajoute .99999 (en fait jamais 1 autrement
                                                                                                    // s'il a 98 points d'exp, il levelUp alors qu'il faut pas
        return required_exp;
    }

    // Méthode decreaseHealth
    public int decreaseHealth(int damageTaken) { // Appelée lorsqu'il y a combat
        setHealth(getHealth() - damageTaken);
        return health;
    }

    //--------------------// Méthodes d'action du héro //--------------------//
    // Méthode figthing
    public Boolean fighting(Enemy enemy) {
        
        while (this.getHealth() > 0 && enemy.getHealth() > 0) {
            enemy.decreaseHealth(this.getAttackPower()); // Le héro attaque en premier
            if (enemy.getHealth() > 0) { // Vérifie si l'ennemi a toujours des points de vie
                this.decreaseHealth(enemy.getAttackPower()); // L'ennemi attaque par la suite
            } 
        }

        // On regarde si le héro est mort après le combat
        if (this.getHealth() <= 0) { 
            return false; 
        } else {
            this.experience += enemy.getExperience(); // On ajoute l'experience gagnée suite à la victoire du héro
            enemy.statsUpdate(); // On update les stats du prochain ennemi (même s'il y en a pas)
            return true;
        }
    }

    // Méthode resting
    public void resting() { setHealth(maxHealth); } // Remet les points de vie du héro au maximum

    // Méthode healing
    public void healing(int healingPoint) {
        int newHealth = getHealth() + healingPoint;
        if (newHealth >= getMaxHealth()) { // Vérifie que le soin ne depasse pas la limite maxHealth
            setHealth(maxHealth);
        } else {
            setHealth(newHealth);
        }
    }

    // Méthode training
    public void training(int attackTrainingBonus) { setAttackPower(getAttackPower() + attackTrainingBonus); } // Augmente les points d'attaque du Hero
}
