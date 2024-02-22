public class ArgsProcessor {
    public static void process(String[] args) {
        String[] phrase = makePhrase(args[0]);

        Hero hero = createHero(phrase, phrase[0]); // Creation de l'instance Hero dépendamment du nom
        readActions(phrase, hero);

        // On crée les phrases pour les 2 cas possibles
        if (hero.getHealth() <= 0) {
            System.out.println("In his quest, " + phrase[0] + " died after beating " + hero.numberOfEnemiesDefeated + " enemies and attaining level " + hero.getLevel() + "!");
        } else {
            System.out.println("In his quest, " + phrase[0] + " beat " + hero.numberOfEnemiesDefeated + " enemies, attained level " + hero.getLevel() + " and survived with " + hero.health + " HP!");
        }
    }

    // Méthode createHero
    private static Hero createHero(String[] phrase, String nom) {
        switch (nom.charAt(0)) {
            case 'A':
                return new HeroAttaque(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2]));
            case 'D':
                return new HeroDefense(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2]));
            default:
                return new HeroEquilibre(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2]));
        }
    }

    // Méthode readActions
    private static void readActions(String[] sentence, Hero hero) {
        for (int i = 3; i < sentence.length; i++) { // 3ième position dans le tableau est la première phrase conçernant l'action 
            doAction(sentence[i], hero);
            if (hero.getHealth() <= 0) { 
                break; 
            }
        }
    }

    private static String[] makePhrase(String args) {
        return args.trim().split(",");
    }

    // méthode qui prend la partie de la phrase qui décrit l'action et le héros, puis effectue l'action correspondante
    // retourne true si le joueur survit à l'action, false sinon
    private static boolean doAction(String action, Hero hero) {
        // ici, on transforme le String action en un tableau de String, en séparant les mots par des espaces
        String[] phrase = action.trim().split(" ");
        // le type d'action est déterminé par le premier mot de la phrase
        switch (phrase[0]) {
            case "fought":
                int numberOfEnemies = Integer.parseInt(phrase[1]); // On stock le nombre d'ennemi dans la phrase
                if (numberOfEnemies <= 0) {
                    throw new IllegalArgumentException("Il n'y a aucun ennemi à combattre.");
                }

                for (int i = 0; i < numberOfEnemies; i++) {
                    Enemy enemy = new Enemy(); // Création d'un nouvel ennemi
                    enemy.updateStatsBasedOnDefeats(hero.numberOfEnemiesDefeated); // On améliore les stats de la nouvelle instance

                    if (!hero.fighting(enemy)) { // Héro meurt -> on stop
                        return false;
                    }

                    hero.levelUp(); // On améliore le héro
                }
                break;

            case "rested":
                hero.resting();
                break;

            case "healed":
                hero.healing(Integer.parseInt(phrase[1]));
                break;

            case "trained":
                hero.training(Integer.parseInt(phrase[3]));
                break;
        }
        return true;
    }
}
