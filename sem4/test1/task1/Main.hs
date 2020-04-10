infinityList:: [Integer]
infinityList = 1 : (-1) : infinityList

naturalNumbers:: [Integer]
naturalNumbers = [1..]

resultFunction::[Integer]
resultFunction = zipWith (*) infinityList naturalNumbers