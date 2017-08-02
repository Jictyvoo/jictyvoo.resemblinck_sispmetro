#include <stdio.h>
#include "dina_string.h"
#include <string.h>
#include <math.h>

float convertNumber(char* textNumber){
	float num = 0;
	int decimals = 1;
	unsigned short int decimal = 0;	/*Funcionamento de Bool*/
	int position = 0;
	for(position = 0 ; position < strlen(textNumber); position += 1) {
		if(textNumber[position] == '.')
			decimal = 1;	/*Funcionamento de Bool*/
		else if(textNumber[position] >= '0' && textNumber[position] <= '9') {
			if(decimal == 0)
				num *= 10;
			num += (textNumber[position] - 48) / (decimal == 1 ? pow(10, decimals++) : 1);
		}
	}
	return num;
}

unsigned short int foundCoordinates(char*fileLine, float* xy){
	unsigned short int open = 0;	/*boolean*/
	int size = 0;
	int position = 0;
	char identifie[2];
	identifie[0] = 0;
	identifie[1] = 0;
	unsigned short int quantity = 0;
	char* coordinate = (char*) malloc(sizeof(char) * size);
	for(position = 0; position < strlen(fileLine); position += 1){
		if(identifie[0] == 'x' && identifie[1] == '='){
			if(fileLine[position] == '"'){
				open = !open;
			}
			if(open){
				coordinate = (char *) realloc((coordinate), (++size) * sizeof(char));
				coordinate[size - 1] = fileLine[position];
			}
			else if(!open){
				if(size > 1){
					xy[quantity++] = convertNumber(coordinate);
					size = 1;
					if(quantity >= 2)
						return 1;
				}
			}
		}
		else{
			identifie[0] = identifie[1];
			identifie[1] = fileLine[position];
		}
	}
	return 0;
}

int main(){
	fprintf(stdout, "Insira o nome do arquivo de entrada: ");
	char* inputName;
	dina_scan(&inputName);
	
	FILE* svgFile = fopen(inputName, "r");
	libera_dina(inputName);
	if(svgFile == NULL)
		return 0;
	FILE* outputFile = fopen("Coordinates.csv", "w");
	
	char* inFile = NULL;
	while(!feof(svgFile)){
		float xy[2] = {0, 0};
		fdina_scan(svgFile ,&inFile, '>');
		if(foundCoordinates(inFile, xy))
			fprintf(outputFile, "ESTACAO;%.2f;%.2f\n", xy[0], xy[1]);
		libera_dina(inFile);
		inFile = NULL;
	}
	libera_dina(inFile);
	fprintf(stdout, "Concluido");
	fclose(svgFile);
	fclose(outputFile);
	return 0;
}
