#Вариант 15
Условие задания:

В обобщённой матрице Вандермонда bk = k / 2, ak = (n + 1 − k) − 4, k=1,...n

Результаты работы программы:

|  n 	|        cond(A)        	|      cond(B)eigen     	|    cond(B)newton   	|     B(eigen)^2 - A     	|     B(newton)^2 - A    	|
|:--:	|:---------------------:	|:---------------------:	|:------------------:	|:----------------------:	|:----------------------:	|
| 2  	| 11.020833333333334    	| 3.845977561517017     	| 3.8459775615170178 	| 2.185751579730777E-16  	| 5.898059818321144E-17  	|
| 3  	| 157.07912927211885    	| 13.44436821469205     	| 13.44436821469202  	| 4.527146404667251E-16  	| 2.672269234597439E-15  	|
| 4  	| 4161.412736852068     	| 83.64857625867417     	| 83.64857626008073  	| 1.0215438281843242E-12 	| 1.2045483293141988E-11 	|
| 5  	| 186281.4421317896     	| 825.9911010197343     	| 825.9910347643721  	| 9.333852653195163E-11  	| 2.835095516374423E-8   	|
| 6  	| 1.2973314117536284E7  	| 11487.270292503961    	| 78.9633811891333   	| 5.0493835744432674E-9  	| 0.0032279354466495896  	|
| 7  	| 1.2717783680125887E9  	| 204291.14806604874    	| 108.1537685733985  	| 9.124445502029576E-7   	| 0.003545332288994941   	|
| 8  	| 1.6289363692181274E11 	| 4420615.351769348     	| 173.67205694160475 	| 3.4633914207336173E-4  	| 0.0035223156344945245  	|
| 9  	| 2.6271649840259133E13 	| 3.700274120669432E8   	| 246.9209581307561  	| 0.12919624802452148    	| 0.003341206706980144   	|
| 10 	| 5.254948847362984E15  	| 1.2922277019323156E10 	| 6.029235047352838  	| 0.24222398801468806    	| 0.013684298848196986   	|

У исходной матрицы числа обусловленности достаточно большие. У матриц, которые были найдены методом собственных чисел и векторов не очень большие и хорошая погрешность. У матриц, которые были найдены методом Ньютона, числа обусловленности на порядок меньше и гораздо хуже погрешность начиная с n = 6.