package org.mycompany;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.persistence.criteria.*;

@Controller
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	private final Map<Integer, Customers> customers = new TreeMap<>();

	/*
	 * public CustomerServiceImpl() { customers.put(1, new Customer(1, "Monica",
	 * "Hockelberg", "mhockelb@redhat.com")); customers.put(2, new Customer(2,
	 * "John", "Doe", "jdoe@gmail.com")); customers.put(3, new Customer(3, "Jane",
	 * "Smith", "jsmith@gmail.com")); }
	 */

	public CustomerServiceImpl() {
		/*
		 * customers.put(1, new Customer(1, "Monica", "Hockelberg",
		 * "mhockelb@redhat.com")); customers.put(2, new Customer(2, "John", "Doe",
		 * "jdoe@gmail.com")); customers.put(3, new Customer(3, "Jane", "Smith",
		 * "jsmith@gmail.com"));
		 */
	}

	@Override
	public Customers findCustomer(Integer id) {
		System.out.println("1- HERE'S THE ID=" + id);
		return customerRepository.findOne(id);
	}

	/*
	 * @Override public Collection<Customer> findCustomers() { return
	 * customers.values(); }
	 */

	@Override
	public Iterable<Customers> findCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void updateCustomer(Customers customer) {
		customerRepository.save(customer);
		// customers.put(customer.getId(), customer);
	}

}
