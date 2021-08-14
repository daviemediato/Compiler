package compiler;

import compiler.syntatic.Syntatic;

public class Compiler {

    public static void main(String[] args) {
        try {
            Syntatic parser;
            String defaultFile = "./teste1_1.txt";
            if (args.length == 1)
                parser = new Syntatic(args[0]);
            else
                parser = new Syntatic(defaultFile);

            parser.run();

            System.out.println("Syntatic Success");

        } catch (Exception e) {
            System.out.println("Syntatic Error Detected");
            System.out.println(e.getMessage());
        }
    }

}
