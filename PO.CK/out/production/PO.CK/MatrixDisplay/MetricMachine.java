package MatrixDisplay;
/**
 * @author Ahmed Metwally
 *
 */

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SuppressWarnings("WeakerAccess")
public class MetricMachine{
    private static ASTVisitorMod parse(char[] str) {
        ASTParser parser = ASTParser.newParser(AST.JLS4);
        parser.setSource(str);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        IProblem[] problems = cu.getProblems();
        for (IProblem problem : problems) {
        }

        ASTVisitorMod visitor = new ASTVisitorMod();
        cu.accept(visitor);

        return visitor;
    }


    private static char[] ReadFileToCharArray(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();

        return fileData.toString().toCharArray();
    }

    private static List<char[]> ParseFilesInDir(List<String> JavaFiles) throws IOException {
        if (JavaFiles.isEmpty()) {
            System.exit(0);
        }

        List<char[]> FilesRead = new ArrayList<char[]>();

        for (int i = 0; i < JavaFiles.size(); i++) {
            FilesRead.add(ReadFileToCharArray(JavaFiles.get(i)));
        }

        return FilesRead;
    }


    public static HalsteadMetrics getMetrics(String pathDirectory) throws IOException {

        List<char[]> FilesRead = ParseFilesInDir(Collections.singletonList(pathDirectory));

        ASTVisitorMod ASTVisitorFile;
        int DistinctOperators = 0;
        int DistinctOperands = 0;
        int TotalOperators = 0;
        int TotalOperands = 0;
        int OperatorCount = 0;
        int OperandCount = 0;


        for (int i = 0; i < FilesRead.size(); i++) {

            ASTVisitorFile = parse(FilesRead.get(i));
            DistinctOperators += ASTVisitorFile.oprt.size();
            DistinctOperands += ASTVisitorFile.names.size();

            OperatorCount = 0;
            for (int f : ASTVisitorFile.oprt.values()) {
                OperatorCount += f;
            }
            TotalOperators += OperatorCount;

            OperandCount = 0;
            for (int f : ASTVisitorFile.names.values()) {
                OperandCount += f;
            }
            TotalOperands += OperandCount;

        }

        HalsteadMetrics hal = new HalsteadMetrics();

        hal.setParameters(DistinctOperators, DistinctOperands, TotalOperators, TotalOperands);
        return hal;
    }
}