import java.util.ArrayList;
import java.util.List;


public class Lab03{
	
	public static void main(String[] args){
		
		// FUNÇAO DOS TESTES
		// boolean test (boolean a){
		// int b;
		// int c;
		// functionBody
		// return a;
		//}
			
		
		// Declaracão do objeto FunctionDeclaration
		FunctionDeclaration fd = new FunctionDeclaration();
		
		// Composicao da declaração do fd
		
		// 1º - Definimos o tipo do retorno da função
		fd.returnType = new TypeBoolean();
		
		
		
		// 2º - Defini o nome da função
		fd.functionName = new ID("teste");
		
		
		
		// 3º - Definindo os parametros da função
		VariableDeclaration param_A = new VariableDeclaration();
		param_A.variableName = new ID("a");
		param_A.variableType = new TypeBoolean();
		List<VariableDeclaration> param_List = new ArrayList<VariableDeclaration>();
		param_List.add(param_A);
		
		fd.parameterList = param_List;
		
		
		// 4º - Definindo as variaveis da função
		VariableDeclaration var_B = new VariableDeclaration();
		var_B.variableName = new ID("b");
		var_B.variableType = new TypeInt();
		
		VariableDeclaration var_C = new VariableDeclaration();
		var_C.variableName = new ID("c");
		var_C.variableType = new TypeInt();
		List<VariableDeclaration> var_list = new ArrayList<VariableDeclaration>();
		
		var_list.add(var_B);
		var_list.add(var_C);
		fd.variableDeclarationList = var_list;
		
		
		
		// 5º Definindo os statements da função: function body
		
		int idx_test = 1;	// Aterar para escolha dos testes a ser executado
		
		AttributionStatement attributionStatement;
		List<Statement> stm_List = new ArrayList<Statement>();
		
		switch(idx_test)
		{
			case 1:
				// a = b < c
				attributionStatement = new AttributionStatement(new ID("a"), new LessExpression(
					new VariableExpression(new ID("b")), new VariableExpression(new ID("c"))
					
				));
				
				stm_List.add(attributionStatement);
				
				break;
		
			case 2:
				// c = (b + c) < (b - c)
				attributionStatement = new AttributionStatement( new ID("c"), new LessExpression(
					new PlusExpression(new VariableExpression(new ID("b")), new VariableExpression(new ID("c"))),
					new MinusExpression(new VariableExpression(new ID("b")), new VariableExpression(new ID("c")))
				
				));
				
				stm_List.add(attributionStatement);
				
				break;
				
			case 3:
					// a = (b + c) < (b < c)
					attributionStatement = new AttributionStatement(new ID("a"), new LessExpression(
						new PlusExpression(new VariableExpression(new ID("b")), new VariableExpression(new ID("c"))),
						new LessExpression(new VariableExpression(new ID("b")), new VariableExpression(new ID("c")))
						
					));
					
					stm_List.add(attributionStatement);
				break;
		
			
			default:
			
				break;

		}
		
		fd.functionBody = stm_List;
		
		// 6º - Definindo a expressão de retorno
		Expression ret_expression = new VariableExpression(new ID("a"));
		fd.returnExpression = ret_expression;
		
		boolean resultado = new checkType().correctTypes(fd);
		
		System.out.println(" ********* RETOURNO DO CORRECT TYPES  ***********");
		System.out.println(resultado);
		
	}
}