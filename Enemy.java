public class Enemy {

    // Attributs privés
    private int pointDeVie = 10;
    private int pointAttaque = 25;
    private int pointExperience = 35;

    // Constructeur
    public Enemy(int pointDeVie, int pointAttaque, int pointExperience) {
        this.pointDeVie = pointDeVie;
        this.pointAttaque = pointAttaque;
        this.pointExperience = pointExperience;
    }

    private void ameliorationStats() { // La fonction fight() de la classe héro appel cette fonction si le héro bat le monstre
        this.pointDeVie += 10;
        this.pointAttaque += 5;
        this.pointExperience += 8;
    }
}
