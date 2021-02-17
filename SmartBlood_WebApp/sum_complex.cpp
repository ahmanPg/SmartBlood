// using namespace std;
#include<iostream>
class complex
{
	int re,im;
	public:
		void get()
		{
			std::cin>>re>>im;
		}
		void disp()
		{
			std::cout<<re<<"+"<<im<<"i";
 
		}
		void sum(complex,complex);
};

void complex::sum(complex c1,complex c2)
{
	re=c1.re+c2.re;
	im=c1.im+c2.im;
}

int main()
{
	complex c1,c2,c3;
	std::cout<<"Enter 1st complex no.:";
	c1.get();
	std::cout<<"Enter 2nd complex no.:";
	c2.get();
	std::cout<<"The 1st complex no. is";
	c1.disp();
	std::cout<<"\nThe 2nd complex no. is";
	c2.disp();
	c3.sum(c1,c2);
	std::cout<<"\nThe resultant complex no. is";
	c3.disp();
}