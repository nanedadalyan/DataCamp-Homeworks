{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": [
    "/* 1. Create supermarket basket like function which creates fixed size array and\n",
    "appends customer products  to this basket printing products. \n",
    "Basket becomes full if products in the basket are  more than  5.\n",
    "If basket is full throw exception by printing “Basket is  full”.*/\n",
    "\n",
    "import io.StdIn._\n",
    "def inBasket(): Unit=\n",
    "{\n",
    "  var basket= new Array[String](5)\n",
    "  var counter=0\n",
    "  var prod=\"\"\n",
    "  try\n",
    "  {\n",
    "    while (true)\n",
    "    {\n",
    "      prod=readLine(\"PLease insert your product \")\n",
    "      basket(counter)=prod\n",
    "      //vonc tpenq menak voch null arjeqner@? filter porceci chexav\n",
    "      println(basket.mkString(\",\"))\n",
    "      counter=counter+1\n",
    "    } \n",
    "  }\n",
    "  catch\n",
    "  {\n",
    "    case full:ArrayIndexOutOfBoundsException => throw new Exception(\"Sorry the Basket is full\")\n",
    "  }\n",
    "}\n",
    "inBasket()\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 1,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Please insert Int"
     ]
    },
    {
     "name": "stdin",
     "output_type": "stream",
     "text": [
      " 5\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "\u001b[32mimport \u001b[39m\u001b[36mio.StdIn._\n",
       "\u001b[39m\n",
       "defined \u001b[32mfunction\u001b[39m \u001b[36mStrToInt\u001b[39m\n",
       "\u001b[36mres0_2\u001b[39m: \u001b[32mInt\u001b[39m = \u001b[32m5\u001b[39m"
      ]
     },
     "execution_count": 1,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "/* 2. Create function for converting strings to int. \n",
    "Catch exception if confersion can not be done, otherwise return the converted value.*/\n",
    "\n",
    "import io.StdIn._\n",
    "def StrToInt(): Int =\n",
    "{\n",
    "  var str=readLine(\"Please insert Int\")\n",
    "  try\n",
    "  {\n",
    "    str.toInt\n",
    "  }\n",
    "  catch\n",
    "  {\n",
    "    case mycase:NumberFormatException => throw new Exception(\"I said please insert Int\")\n",
    "  }\n",
    "}\n",
    "StrToInt()\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 3,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "List(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z)\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "defined \u001b[32mfunction\u001b[39m \u001b[36mletters\u001b[39m"
      ]
     },
     "execution_count": 3,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "/* 4. Make a List[Char] that contains ’a’-’z’ without typing in all the characters. (Use toChar to make this work.)*/\n",
    "def letters(): List[String]=\n",
    "{\n",
    "  var myList= List[String]()\n",
    "  var i=1\n",
    "  while (i<=26)\n",
    "   {\n",
    "     myList=myList:+(i+64).toChar.toString\n",
    "     i=i+1\n",
    "   }\n",
    "   myList\n",
    "}\n",
    "println(letters())"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 5,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "3\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "defined \u001b[32mfunction\u001b[39m \u001b[36mavg\u001b[39m"
      ]
     },
     "execution_count": 5,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "/* 5. Write a function that takes a number of values and returns the average\n",
    "excluding the largest and smallest values. */\n",
    "\n",
    "def avg(arr:Array[Int]): Unit=\n",
    "{\n",
    "  if (arr.length<3)\n",
    "  {\n",
    "    println (\"please instert at least three numbers\")\n",
    "  }\n",
    "  else\n",
    "  {\n",
    "    try\n",
    "    {\n",
    "      var helper=(arr.filter(_!=arr.min)).filter(_!=arr.max)\n",
    "      println(helper.sum/helper.length)\n",
    "    }\n",
    "    catch\n",
    "    {\n",
    "      case a:ArithmeticException => throw new Exception(\"please instert at least three Different numbers\")\n",
    "    }\n",
    "  }\n",
    "}\n",
    "avg(Array(1,2,3,4,5))\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": [
    "/* 6. https://www.hackerrank.com/challenges/array-left-rotation/problem */\n",
    "\n",
    "\n",
    "def leftStep(arr:Array[Int],step:Int): Array[Int]=\n",
    "{\n",
    "  //vonc filter anenq indexic kaxvac?\n",
    "   return arr.slice(step,arr.length)++arr.slice(0,step)\n",
    "}  \n",
    "leftStep(Array(1,2,3,4,5),1)\n",
    "//1,2,3,4,5\n",
    "//2,3,4,5,1 1\n",
    "//3,4,5,1,2 2\n",
    "//4,5,1,2,3 3\n",
    "//5,1,2,3,4 4  \n",
    "//1,2,3,4,5 5"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 13,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "defined \u001b[32mfunction\u001b[39m \u001b[36mcountA\u001b[39m"
      ]
     },
     "execution_count": 13,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "def countA(str:String,num:Int):Unit=\n",
    "{\n",
    "  var counter=0\n",
    "  var i=0\n",
    "  var newStr=str\n",
    "  if (str.length<num)\n",
    "  { \n",
    "    //if possible join the whole word\n",
    "    while(num%str.length==0)\n",
    "    {\n",
    "      newStr=newStr+str\n",
    "    }\n",
    "    //join to newStr letters one by one\n",
    "    for(i<-0 to num-newStr.length)\n",
    "    {\n",
    "      newStr=newStr+str(i)\n",
    "    }\n",
    "    //counting \"a\" in new string \n",
    "    for (i <- newStr)\n",
    "    {\n",
    "      if (i.toString==\"a\")\n",
    "      {\n",
    "        counter=counter+1\n",
    "      }\n",
    "    }\n",
    "    println(counter)\n",
    "  }\n",
    "  else\n",
    "  {\n",
    "    println(\"num should be greater than the length of string\")\n",
    "  }\n",
    "}\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": [
    "countA(\"aaaaaaaaaa\",30)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Scala (2.12)",
   "language": "scala",
   "name": "scala212"
  },
  "language_info": {
   "codemirror_mode": "text/x-scala",
   "file_extension": ".scala",
   "mimetype": "text/x-scala",
   "name": "scala",
   "nbconvert_exporter": "script",
   "version": "2.12.10"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 2
}
