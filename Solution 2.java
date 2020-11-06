package advancedProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

interface User {

	public void display();

	public String getName();

	public float getReward();

	public void addReward(int rewardpts);
}

class Restaurant implements User {

	protected String name, address, category;

	protected float rewardPoints;

	protected float offer;

	protected float numOrders;

	protected HashMap<Integer, FoodItem> foodItems = new HashMap<Integer, FoodItem>();

	protected HashMap<Integer, Float> foodItemsQuantity = new HashMap<Integer, Float>();

	Restaurant(String name, String address) {

		this.name = name;
		this.address = address;
		this.category = "Rest";
		this.rewardPoints = 0;
		this.offer = 0;
		this.numOrders = 0;
	}
	
	public void updateQuantity(float x, int id) {
		this.foodItemsQuantity.put(id, foodItemsQuantity.get(id)-x);
	}

	public int getReward(int value) {
		
		int x = 5 * (value/100);
		return x;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void incOrdersCount() {
		this.numOrders++;
	}

	public void addFoodItem(FoodItem foodItem, float q) {
		foodItems.put(foodItem.getID(), foodItem);
		foodItemsQuantity.put(foodItem.getID(), q);
	}

	public void displayFoodItems() {

		for (Integer i : foodItems.keySet()) {
			foodItems.get(i).display();
			System.out.println("   Quantity: " + foodItemsQuantity.get(i));
		}
	}

	public void updateFoodAttribute(int code, int no, float new_val) {

		if (no == 2) {
			foodItems.get(code).setPrice(new_val);
		} else if (no == 3) {
			foodItemsQuantity.put(code, new_val);
		} else {
			foodItems.get(code).setOffer(new_val);
		}

		this.foodItems.get(code).display();
		System.out.println("   Quantity: " + foodItemsQuantity.get(code));
	}

	public void updateFoodAttribute(int code, int no, String new_val) {

		if (no == 1) {
			foodItems.get(code).setName(new_val);
		} else {
			foodItems.get(code).setCategory(new_val);
		}

	}

	@Override
	public float getReward() {
		return rewardPoints;
	}

	public void setOffer(float Offer) {
		offer = 0;
	}

	@Override
	public void display() {

		System.out.println(this.name + " " + this.address + " " + numOrders);
	}

	public FoodItem getFoodByCode(int j) {

		return foodItems.get(j);
	}

	public String getCategory() {
		return this.category;
	}

	public float getDiscount() {
		return this.offer;
	}

	@Override
	public void addReward(int rewardpts) {
		this.rewardPoints += rewardpts;
	}
}

class AuthenticRestaurant extends Restaurant {

	AuthenticRestaurant(String name, String add) {
		super(name, add);
		offer = 0;
		category = "Authentic";
	}

	@Override
	public void setOffer(float Offer) {
		this.offer = Offer;
	}

	@Override
	public int getReward(int value) {
		
		int x = 25 * (value/200);
		System.out.println(value + " " + x);
		return x;
	}
}

class FastFoodRestaurant extends Restaurant {

	FastFoodRestaurant(String name, String add) {
		super(name, add);
		offer = 0;
		category = "Fast Food";
	}

	@Override
	public void setOffer(float Offer) {
		this.offer = Offer;
	}

	@Override
	public int getReward(int value) {
		
		int x = 10 * (value / 150);
		return x;
	}
}

class Customer implements User {

	protected String name, address, category;

	protected float deliveryCharge;

	protected float accountBalance;
	
	protected int rewardPoints;

	protected Cart cart;

	Customer(String name, String address) {

		cart = new Cart();
		this.name = name;
		this.address = address;
		this.category = "Rest";
		this.rewardPoints = 0;
		this.accountBalance = 1000;
		this.deliveryCharge = 40;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getReward() {
		return this.rewardPoints;
	}

	@Override
	public void display() {

		System.out.println(this.name + "(" + this.category + ") , " + this.address + " , " + this.accountBalance + "/-");
	}

	public void addFoodToCart(FoodItem foodItem, float q) {
		cart.addFoodItem(foodItem, q);
	}

	public void displayCart() {
		cart.display();
	}

	public float getDeliveryCharge() {
		return this.deliveryCharge;
	}

	public void emptyCart() {
		cart.empty();
	}

	public float getBill() {

		float val = cart.bill();

		if (this.category.equals("Elite") && val > 200) {
			val -= 50;
		}
		if (this.category.equals("Special") && val > 200) {
			val -= 25;
		}

		return val;
	}

	public Cart getCart() {
		return this.cart;
	}

	public int getItemsInCartCount() {
		return cart.itemsCount();
	}

	public void updateAccountBalance(float val) {
		this.accountBalance -= val;
	}

	public void updateRewardPoints(float val) {
		this.rewardPoints -= val;
	}

	@Override
	public void addReward(int rewardpts) {
		this.rewardPoints += rewardpts;
	}
	
	public float getAccountBalance() {
		return this.accountBalance;
	}
}

class EliteCustomer extends Customer {

	EliteCustomer(String name, String address) {
		super(name, address);
		deliveryCharge = 0;
		category = "Elite";
	}
}

class SpecialCustomer extends Customer {

	SpecialCustomer(String name, String address) {
		super(name, address);
		deliveryCharge = 20;
		category = "Special";
	}
}

class Company {

	private float balance;

	private float deliveryCharges;

	Company() {

		this.balance = 0;
		this.deliveryCharges = 0;
	}

	public float getBalance() {
		return this.balance;
	}

	public float getDeliveryCharges() {
		return this.deliveryCharges;
	}

	public void addBalance(float addBal) {
		this.balance += addBal;
	}

	public void addDeliveryCharges(float delChrg) {
		this.deliveryCharges += delChrg;
		this.balance += delChrg;
	}
}

class FoodItem {

	private Restaurant restaurant;
	private String name, category;
	private float price, offer;
	private final int Id;

	FoodItem(Restaurant restaurant, String name, float price, String category, float offer, int Id) {

		this.restaurant = restaurant;
		this.name = name;
		this.price = price;
		this.category = category;
		this.offer = offer;
		this.Id = Id;
	}

	public void display() {

		System.out.print("ID: " + Id + "   Name: " + this.name + "   Price: " + this.price + "   Category: " + this.category + "   Discount: " + this.offer + " % off");
	}

	public int getID() {
		return this.Id;
	}

	public void setOffer(float off) {
		this.offer = off;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(String cat) {
		this.category = cat;
	}

	public float getAmount() {
		return (price - ((price * this.offer) / 100));
	}

	public float getRestaurantDiscount() {
		return this.restaurant.getDiscount();
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public String getName() {
		return this.name;
	}
}

class Cart {

	protected HashMap<Integer, FoodItem> foodItems = new HashMap<Integer, FoodItem>();

	protected HashMap<Integer, Float> foodItemsQuantity = new HashMap<Integer, Float>();

	public void addFoodItem(FoodItem fooditem, float q) {
		this.foodItems.put(fooditem.getID(), fooditem);
		this.foodItemsQuantity.put(fooditem.getID(), q);
	}

	public void display() {

		for (Integer i : foodItems.keySet()) {
			foodItems.get(i).display();
			System.out.println("   Quantity: " + foodItemsQuantity.get(i));
		}
	}

	public void empty() {
		
		foodItems.clear();
		foodItemsQuantity.clear();
	}

	public float bill() {

		float billVal = 0;

		for (Integer i : foodItems.keySet()) {
			billVal += foodItems.get(i).getAmount() * foodItemsQuantity.get(i);
		}

		for (Integer i : foodItems.keySet()) {
			billVal = billVal - (billVal * foodItems.get(i).getRestaurantDiscount()) / 100;
			break;
		}
		
		for (Integer i : foodItems.keySet()) {
			if (foodItems.get(i).getRestaurant().getCategory().equals("Authentic") && billVal > 100) {
				billVal -= 50;
			}
			break;
		}
		
		return billVal;
	}

	public Restaurant getRestaurant() {
		
		int x = 0;
		
		for (Integer i : foodItems.keySet()) {
			x = i;
			break;
		}
		
		return foodItems.get(x).getRestaurant();
	}

	public int itemsCount() {

		int cnt = 0;

		for (Integer i : foodItems.keySet()) {
			cnt += foodItemsQuantity.get(i);
		}

		return cnt;
	}

	public ArrayList<OrderNode> getOrderList() {

		ArrayList<OrderNode> ordList = new ArrayList<OrderNode>();

		for (Integer i : foodItems.keySet()) {
			ordList.add(new OrderNode(foodItems.get(i).getName(), foodItems.get(i).getRestaurant().getName(),foodItemsQuantity.get(i)));
		}

		return ordList;
	}
}

class OrderNode {

	private final String name, restaurant;
	private final float quantity;

	OrderNode(String name, String restaurant, float quantity) {

		this.name = name;
		this.restaurant = restaurant;
		this.quantity = quantity;
	}

	public void display() {

		System.out.println(
				"Bought item: " + this.name + ", quantity: " + quantity + ", from Restaurant " + this.restaurant);
	}
}

class Orders {

	private ArrayList<ArrayList<OrderNode>> OrderList = new ArrayList<ArrayList<OrderNode>>();

	private ArrayList<Float> billList = new ArrayList<Float>();

	private ArrayList<Float> deliveryChargeList = new ArrayList<Float>();

	public void addOrder(ArrayList<OrderNode> orderList, float bill, float deliveryCharge) {

		this.OrderList.add(orderList);
		this.billList.add(bill);
		this.deliveryChargeList.add(deliveryCharge);
	}

	public void displayOrders() {

		if (OrderList.size() > 10) {

			for (int i = OrderList.size() - 1; i >= OrderList.size() - 10; i--) {

				for (int j = 0; j < OrderList.get(i).size(); i++) {
					OrderList.get(i).get(j).display();
				}

				System.out.println("These items were bought for INR - " + billList.get(i)
						+ "/-  with delivery charge INR - " + deliveryChargeList.get(i) + "/-");
			}

		} else {

			for (int i = OrderList.size() - 1; i >= 0; i--) {

				for (int j = 0; j < OrderList.get(i).size(); j++) {
					OrderList.get(i).get(j).display();
				}

				System.out.println("These items were bought for INR - " + billList.get(i)
						+ "/-  with delivery charge INR - " + deliveryChargeList.get(i) + "/-");
			}
		}
	}
}

class App {

	private ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>(5);

	private ArrayList<Customer> Customers = new ArrayList<Customer>(5);

	Company company;
	Orders orders;

	public static int globalID = 0;

	App() {

		Restaurant r1 = new AuthenticRestaurant("Shah", "Pune");
		Restaurants.add(r1);

		Restaurant r2 = new Restaurant("Ravi's", "New Delhi");
		Restaurants.add(r2);

		Restaurant r3 = new AuthenticRestaurant("The Chinese", "Hyderabad");
		Restaurants.add(r3);

		Restaurant r4 = new FastFoodRestaurant("Wang's", "Chennai");
		Restaurants.add(r4);

		Restaurant r5 = new Restaurant("Paradise", "Mumbai");
		Restaurants.add(r5);

		Customer c1 = new EliteCustomer("Ram", "Manali");
		Customers.add(c1);

		Customer c2 = new EliteCustomer("Sam", "Lucknow");
		Customers.add(c2);

		Customer c3 = new SpecialCustomer("Tim", "Kasol");
		Customers.add(c3);

		Customer c4 = new Customer("Kim", "Ahemdabad");
		Customers.add(c4);

		Customer c5 = new Customer("Jim", "Chandigarh");
		Customers.add(c5);

		company = new Company();
		orders = new Orders();
	}

	private void displayQueries() {

		System.out.println("1) Enter as Restaurant Owner");
		System.out.println("2) Enter as Customer");
		System.out.println("3) Check User Details");
		System.out.println("4) Company Account details");
		System.out.println("5) Exit");
	}

	private void displayRestaurants() {

		System.out.println("1) Shah (Authentic)");
		System.out.println("2) Ravi’s");
		System.out.println("3) The Chinese (Authentic)");
		System.out.println("4) Wang’s (Fast Food)");
		System.out.println("5) Paradise");
	}

	private void displayRestaurantsOptions() {

		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) Print Rewards");
		System.out.println("4) Discount on bill value");
		System.out.println("5) Exit");
	}

	private void displayAttributes() {

		System.out.println("Choose an attribute to edit:");

		System.out.println("1) Name");
		System.out.println("2) Price");
		System.out.println("3) Quantity");
		System.out.println("4) Category");
		System.out.println("5) Offer");
	}

	private void displayCustomers() {

		System.out.println("1) Ram (Elite)");
		System.out.println("2) Sam (Elite)");
		System.out.println("3) Tim (Special)");
		System.out.println("4) Kim");
		System.out.println("5) Jim");
	}

	private void displayCustomerMenu() {

		System.out.println("Customer Menu");
		System.out.println("1) Select Restaurant");
		System.out.println("2) checkout cart");
		System.out.println("3) Reward won");
		System.out.println("4) print the recent orders");
		System.out.println("5) Exit");
	}

	public void run() throws RuntimeException, IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to Zotato:");
		displayQueries();

		int query = Integer.parseInt(reader.readLine());

		while (true) {

			if (query == 1) {

				displayRestaurants();

				int i = Integer.parseInt(reader.readLine());

				System.out.println("Welcome " + Restaurants.get(i - 1).getName());

				displayRestaurantsOptions();

				int j = Integer.parseInt(reader.readLine());

				while (true) {

					if (j == 1) {

						System.out.println("Enter food item details");

						System.out.println("Food name:");
						String food_name = reader.readLine();

						System.out.println("Item price:");
						float food_price = Float.parseFloat(reader.readLine());

						System.out.println("Item quantity :");
						float food_quantity = Float.parseFloat(reader.readLine());

						System.out.println("Item category:");
						String food_cat = reader.readLine();

						System.out.println("Offer:");
						float food_offer = Float.parseFloat(reader.readLine());

						FoodItem foodItem = new FoodItem(Restaurants.get(i - 1), food_name, food_price, food_cat,
								food_offer, ++globalID);

						Restaurants.get(i - 1).addFoodItem(foodItem, food_quantity);

						foodItem.display();
						System.out.println("   Quantity: " + food_quantity);

						System.out.println("Welcome " + Restaurants.get(i - 1).getName());
						displayRestaurantsOptions();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 2) {

						System.out.println("Choose item by code");

						Restaurants.get(i - 1).displayFoodItems();

						int code = Integer.parseInt(reader.readLine());

						displayAttributes();

						int att_no = Integer.parseInt(reader.readLine());

						if (att_no == 1) {

							System.out.println("Enter the new name - ");
							String new_name = reader.readLine();
							Restaurants.get(i - 1).updateFoodAttribute(code, 1, new_name);

						} else if (att_no == 2) {

							System.out.println("Enter the new price - ");
							float new_price = Float.parseFloat(reader.readLine());
							Restaurants.get(i - 1).updateFoodAttribute(code, 2, new_price);

						} else if (att_no == 3) {

							System.out.println("Enter the new quantity - ");
							float new_quantity = Float.parseFloat(reader.readLine());
							Restaurants.get(i - 1).updateFoodAttribute(code, 3, new_quantity);

						} else if (att_no == 4) {

							System.out.println("Enter the new category - ");
							String new_cat = reader.readLine();

							Restaurants.get(i - 1).updateFoodAttribute(code, 4, new_cat);

						} else {

							System.out.println("Enter the new offer - ");
							float new_offer = Float.parseFloat(reader.readLine());
							Restaurants.get(i - 1).updateFoodAttribute(code, 5, new_offer);
						}

						System.out.println("Welcome " + Restaurants.get(i - 1).getName());
						displayRestaurantsOptions();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 3) {

						System.out.println("Rewards points: " + Restaurants.get(i - 1).getReward());

						System.out.println("Welcome " + Restaurants.get(i - 1).getName());
						displayRestaurantsOptions();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 4) {

						System.out.println("Discount on bill value -");

						float offer = Integer.parseInt(reader.readLine());

						Restaurants.get(i - 1).setOffer(offer);

						System.out.println("Welcome " + Restaurants.get(i - 1).getName());
						displayRestaurantsOptions();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 5) {
						break;
					}
				}

				System.out.println("Welcome to Zotato:");
				displayQueries();

				query = Integer.parseInt(reader.readLine());

			} else if (query == 2) {

				displayCustomers();

				int i = Integer.parseInt(reader.readLine());

				System.out.println("Welcome " + Customers.get(i - 1).getName());
				displayCustomerMenu();

				int j = Integer.parseInt(reader.readLine());

				while (true) {

					if (j == 1) {

						System.out.println("Choose Restaurant");
						displayRestaurants();

						int k = Integer.parseInt(reader.readLine());

						System.out.println("Choose item by code");
						Restaurants.get(k - 1).displayFoodItems();

						int l = Integer.parseInt(reader.readLine());

						System.out.println("Enter item quantity - ");

						float q = Float.parseFloat(reader.readLine());

						Customers.get(i - 1).addFoodToCart(Restaurants.get(k - 1).getFoodByCode(l), q);
						
						Restaurants.get(k - 1).updateQuantity(q, l);

						System.out.println("Items added to cart");

						System.out.println("Welcome " + Customers.get(i - 1).getName());
						displayCustomerMenu();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 2) {

						System.out.println("Items in Cart-");

						Customers.get(i - 1).displayCart();

						System.out.println("Delivery charge - " + Customers.get(i - 1).getDeliveryCharge());

						float tempBill = Customers.get(i - 1).getBill();

						float finalBill = tempBill + Customers.get(i - 1).getDeliveryCharge();
						
						if(finalBill > Customers.get(i - 1).getReward() + Customers.get(i - 1).getAccountBalance()) {
							
							System.out.println("Insufficient balance!!!!!!!!");
							
							Customers.get(i - 1).emptyCart();
							
							System.out.println("All the items removed from cart! Add items to cart again!");
							
							System.out.println("Welcome " + Customers.get(i - 1).getName());
							displayCustomerMenu();

							j = Integer.parseInt(reader.readLine());
							
							break;
						}

						System.out.println("Total order value - INR " + finalBill + "/-");

						System.out.println("1) Proceed to checkout");

						int checkout = Integer.parseInt(reader.readLine());

						if (checkout == 1) {

							company.addDeliveryCharges(Customers.get(i - 1).getDeliveryCharge());

							company.addBalance(tempBill / 100);

							Customers.get(i - 1).getCart().getRestaurant().incOrdersCount();

							float tempFinalBill = (int)finalBill;

							if (Customers.get(i - 1).getReward() > 0) {
								if (Customers.get(i - 1).getReward() >= finalBill) {
									Customers.get(i - 1).updateRewardPoints(finalBill);
									finalBill = 0;
								} else {
									float x = Customers.get(i - 1).getReward(); 
									Customers.get(i - 1).updateRewardPoints(x);
									finalBill -= x;
								}
							}

							System.out.println(Customers.get(i - 1).getItemsInCartCount() + " items successfully bought for INR - " + finalBill + "/-");

							orders.addOrder(Customers.get(i - 1).getCart().getOrderList(), tempFinalBill, Customers.get(i - 1).getDeliveryCharge());

							int x = (int)tempFinalBill;
							
							int rewardPts = Customers.get(i - 1).getCart().getRestaurant().getReward(x);
							
							Customers.get(i - 1).addReward(rewardPts);
							
							Customers.get(i - 1).getCart().getRestaurant().addReward(rewardPts);

							Customers.get(i - 1).updateAccountBalance(finalBill);

							Customers.get(i - 1).emptyCart();
						}

						System.out.println("Welcome " + Customers.get(i - 1).getName());
						displayCustomerMenu();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 3) {

						System.out.println(Customers.get(i - 1).getReward());

						System.out.println("Welcome " + Customers.get(i - 1).getName());
						displayCustomerMenu();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 4) {

						orders.displayOrders();

						System.out.println("Welcome " + Customers.get(i - 1).getName());
						displayCustomerMenu();

						j = Integer.parseInt(reader.readLine());

					} else if (j == 5) {
						break;
					}
				}

				System.out.println("Welcome to Zotato:");
				displayQueries();

				query = Integer.parseInt(reader.readLine());

			} else if (query == 3) {

				System.out.println("1) Customer List");
				System.out.println("2) Restaurant List");

				int listCh = Integer.parseInt(reader.readLine());

				if (listCh == 1) {

					displayCustomers();

					int ch = Integer.parseInt(reader.readLine());

					Customers.get(ch - 1).display();

				} else {

					displayRestaurants();

					int ch = Integer.parseInt(reader.readLine());

					Restaurants.get(ch - 1).display();
				}

				System.out.println("Welcome to Zotato:");
				displayQueries();

				query = Integer.parseInt(reader.readLine());

			} else if (query == 4) {

				System.out.println("Total Company balance - " + company.getBalance() + "/-");

				System.out.println("Total Delivery Charges Collected - " + company.getDeliveryCharges() + "/-");

				System.out.println("Welcome to Zotato:");
				displayQueries();

				query = Integer.parseInt(reader.readLine());

			} else if (query == 5) {

				System.out.println("Exiting the application");

				System.exit(0);
			}
		}
	}
}

public class Assignment_2 {

	public static void main(String[] args) throws RuntimeException, IOException {

		App app = new App();

		app.run();
	}
}