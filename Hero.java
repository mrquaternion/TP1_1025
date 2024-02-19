import java.lang.Math;

public abstract class Hero {

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
    public Hero(int health, int attackPower) {
        setHealth(health);
        this.maxHealth = health;
        setAttackPower(attackPower);
    }

    //--------------------// Getters //--------------------//
    public int getHealth() { return this.health; }

    public int getMaxHealth() { return this.maxHealth; }

    public int getAttackPower() { return this.attackPower; }

    public int getLevel() { return this.level; }

    public int getExperience() { return this.experience; }

    //--------------------// Setters //--------------------//
    private void setLevel(int level) { this.level = level; }

    private void setExperience(int experience) { this.experience = experience; }

    private void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        } else {
            throw new IllegalArgumentException("Les points de vie du héro doivent être supérieur à 0.");
        }
    }

    private void setAttackPower(int attackPower) {
        if (attackPower > 0) {
            this.attackPower = attackPower;
        } else {
            throw new IllegalArgumentException("Les points d'attaque du héro doivent être supérieur à 0.");
        }
    }

    //--------------------// Autres méthodes //--------------------//
    // Méthode levelUp
    public void levelUp() {
        if (experience >= experiencePoints(getLevel())) { // On regarde si le héro peut level up
            this.setExperience(0); // On remet les points d'exp du héro à 0
            this.setLevel(getLevel() + 1); // On augmente le niveau du héro
            this.statsUpdate(); // On augmente les stats du héro
        }
    }

    // Méthode statsUpdate
    public abstract void statsUpdate(); // Doit absolument être implémenté dans les sousclasses

    // Méthode decreaseHealth
    public abstract int decreaseHealth(int damageTaken); // Doit absolument être implémenté dans les sousclasses

    // Méthode experiencePoints
    private static int experiencePoints(int level) {
        int required_exp = (int)((50 + (level + 1) * 20 * Math.pow(1.1, level + 1)) + 0.99999); // Pour prendre l'entier supérieur, on ajoute .99999 (en fait jamais 1 autrement
                                                                                                    // s'il a 98 points d'exp, il levelUp alors qu'il faut pas
        return required_exp;
    }

    //--------------------// Méthodes d'action du héro //--------------------//
    // Méthode figthing
    public Boolean fighting(Enemy enemy) {
        while (this.getHealth() > 0 && enemy.getHealth() > 0) {
            // Le héro attaque en premier
            enemy.decreaseHealth(this.getAttackPower()); 
            if (enemy.getHealth() <= 0) { // Vérifie si l'ennemi a toujours des points de vie
                break; // L'ennemi est mort
            }

            // L'ennemi attaque
            this.decreaseHealth(enemy.getAttackPower()); 
            if (this.getHealth() <= 0) { // Vérifie si le héro a toujours des points de vie
                break; // Le héro est mort
            }
        }

        // On regarde si le héro est mort après le combat
        if (this.getHealth() <= 0) { 
            return false; 
        } else {
            this.experience += enemy.getExperience(); // Le héro gagne de l'expérience
            enemy.statsUpdate(); // Les stats du prochain ennemi sont updater
            return true;
        }
    }

    // Méthode resting
    public void resting() { this.health = getMaxHealth(); } // Remet les points de vie du héro au maximum

    // Méthode healing
    public void healing(int healingPoint) {
        int newHealth = this.getHealth() + healingPoint;
        if (newHealth >= this.getMaxHealth()) { // Vérifie que le soin ne depasse pas la limite maxHealth
            this.health = maxHealth;
        } else {
            this.health = newHealth;
        }
    }

    // Méthode training
    public void training(int attackTrainingBonus) { this.attackPower += attackTrainingBonus; } // Augmente les points d'attaque du Hero
}
