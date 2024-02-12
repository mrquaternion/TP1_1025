public class ArgsProcessor {
    public static void process(String[] args) {
        String[] phrase = makePhrase(args[0]);
        Hero hero;
        String nom = phrase[0];
        switch (nom.charAt(0)) {
            case 'A':
                hero = new HeroAttaque(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
            case 'D':
                 hero = new HeroDefense(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
            default:
                 hero = new HeroEquilibre(Integer.parseInt(phrase[1]), Integer.parseInt(phrase[1]), Integer.parseInt(phrase[2])); // A changer c'est juste pour avoir un héro test.
                break;
        }
                
        System.out.println(hero.getHealth());
        doAction(phrase[3], hero);
        /* ça marche pas 
        for (int i = 3; i < phrase.length; i++){
            doAction(phrase[i], hero);
        }
        */
        
    }
        //TODO : Handle the phrase and output the result
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
                //TODO : Handle the fight
                /* 
                for (int i = 0; i < Integer.parseInt(phrase[1]); i++){
                   
                }*/
                break;
            case "rested":
                //TODO : Handle the resting
                break;
            case "healed":
                //TODO : Handle the healing
                break;
            case "trained":
                //TODO : Handle the training
                break;
        }
        return true;
    }

}

