package Interpreter.CodeGeneration;

import SymbolTable.SymbolTable;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Pattern;

@SuppressWarnings("SingleStatementInBlock")
public class AssemblyConverter {

    private static final Pattern TEMP = Pattern.compile("\\$tmp[0-9]+");

    public AssemblyConverter(String fileName) {
        StringBuilder assembly = new StringBuilder();
        HashMap<String, String> tmpToReg = new HashMap<>();
        int register = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter("source.tiny"));

            String line;
            line = reader.readLine();
            while (line != null) {
                String[] components = line.split(" ");

                for (int i = 0; i < components.length; i++) {
                    if (components[i].matches(TEMP.pattern())) {
                        if (tmpToReg.containsKey(components[i])) {
                            components[i] = tmpToReg.get(components[i]);
                        } else {
                            tmpToReg.put(components[i], "r" + register);
                            components[i] = tmpToReg.get(components[i]);
                            register++;
                        }
                    }
                }

                if (components[0].equals("INT") || components[0].equals("FLOAT")) {
                    assembly.append("var ").append(components[1]);
                } else if (components[0].equals("STR")) {
                    assembly.append("str ").append(components[1]).append(" ").append(components[2]);
                } else if (components[0].equals("DEF")) {
                    assembly.append("label ").append(components[1]);
                } else if (components[0].equals("STOREI") || components[0].equals("STOREF")) {
                    assembly.append("move ").append(components[1]).append(" ").append(components[2]);
                }

                else if (components[0].equals("WRITEI")) {
                    assembly.append("sys writei ").append(components[1]);
                } else if (components[0].equals("WRITEF")) {
                    assembly.append("sys writer ").append(components[1]);
                } else if (components[0].equals("WRITES")) {
                    assembly.append("sys writes ").append(components[1]);
                }

                else if (components[0].equals("READI")) {
                    assembly.append("sys readi ").append(components[1]);
                } else if (components[0].equals("READF")) {
                    assembly.append("sys readr ").append(components[1]);
                }

                if (components[0].equals("ADDI") || components[0].equals("SUBI")
                        || components[0].equals("MULTI") || components[0].equals("DIVI")) {

                    if (components[0].equals("MULTI")) {
                        components[0] = "muli";
                    }


                    assembly.append("move ").append(components[1]).append(" r").append(register).append("\n");
                    assembly.append(components[0].toLowerCase()).append(" ").append(components[2]).append(" r").append(register).append("\n");
                    assembly.append("move r").append(register).append(" ").append(components[3]);
                    register++;
                }

                if (components[0].equals("ADDF") || components[0].equals("SUBF")
                        || components[0].equals("MULTF") || components[0].equals("DIVF")) {

                    switch (components[0]) {
                        case "ADDF":
                            components[0] = "addr";
                            break;
                        case "SUBF":
                            components[0] = "subr";
                            break;
                        case "MULTF":
                            components[0] = "mulr";
                            break;
                        default:
                            components[0] = "divr";
                            break;
                    }


                    assembly.append("move ").append(components[1]).append(" r").append(register).append("\n");
                    assembly.append(components[0].toLowerCase()).append(" ").append(components[2]).append(" r").append(register).append("\n");
                    assembly.append("move r").append(register).append(" ").append(components[3]);
                    register++;
                }
                assembly.append("\n");
                line = reader.readLine();
            }
            writer.write(assembly.toString());
            reader.close();
            writer.close();
            System.out.println(assembly);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
