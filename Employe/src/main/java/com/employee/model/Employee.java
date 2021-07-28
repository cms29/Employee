package com.employee.model;



	public class Employee {
		  
		protected int id;
		protected String name;
		protected String  addres;
		protected int phonenum;
		
		public Employee() {
		}
		
		public Employee(String name, String addres, int phonenum) {
			super();
			this.name = name;
			this.addres = addres;
			this.phonenum = phonenum;
		}

		public Employee(int id,String name, String addres, int phonenum) {
			super();
			
			this.id=id;
			this.name = name;
			this.addres = addres;
			this.phonenum = phonenum;
		}

		public int getId() {
			return id;
		}
		
		
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddres() {
			return addres;
		}
		public void setAddres(String addres) {
			this.addres = addres;
		}
		public int getPhonenum() {
			return phonenum;
		}
		public void setPhonenum(int phonenum) {
			this.phonenum = phonenum;
		}
	}


