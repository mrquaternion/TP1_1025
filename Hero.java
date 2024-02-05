import java.lang.Math;

public class Hero {
    // Attributs privés
    private int health, maxHealth;
    private int pointsAtq;
    private int level = 1;
    private int experience = 0;
    

    // Constructeur
    public Hero(int health, int maxHealth,int pointsAtq) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.pointsAtq = pointsAtq;
    }

    // Getters
    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getLevel() {
        return this.miseNiveau(level);
    }

    public int getExperience() {
        return this.experience;
    }

    
    // Setters


    // Autres méthodes
    private int miseNiveau(int level) {
        if (experience >= pointExperience(level)) {
            this.experience = experience - pointExperience(level); // ***Le hero peut avoir plus que le max de pointExperience()?***
            this.level = level + 1;
        }
        return this.level;
    }

    private int pointExperience(int level) {
        int exp_requis = (int)((50 + (this.level + 1) * 20 * Math.pow(1.1, this.level + 1)) + 1); // Pour prendre l'entier supérieur, on ajoute 1 au résultat
                                                                                                    // et on cast un int sur cette addition (cela va tronquer)
        return exp_requis;
    }

    // Méthodes action
    private String fighting() {
        
    }

    private String resting() {
        return healing();
    }

    private String healing() {
        this.health = maxHealth;
        return Integer.toString(this.health);
    }

    private String training() {
        
    }
}
