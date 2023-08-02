package hackathon.nttdata.coderpath.cursos.controller.validation;


import javax.validation.Validator;
import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import reactor.core.publisher.Mono;

public class CustomerValidator implements BaseValidator<Cursos>{
	
	
	@Override	
	public Mono<Cursos> validate(Cursos t) {
	// TODO Auto-generated method stub
	return null;
}
	
	




	
	
	  /**
	  
	 
	private final Validator<Cursos> validator;
	  
	  public CustomerValidator(){ validator =
	  ValidatorBuilder.of(Cursos.class)
	  .constraint(Cursos::getCompanyEmail, "companyEmail", c->
	  c.notNull().email()).constraint(Cursos::getCompanyName,
	  "companyName", c -> c.notNull()) .constraint(CustomerModel::getTaxId,
	  "taxId", c -> c.pattern("")) .build(); }
	  
	  @Override public Mono<CustomerModel> validate(CustomerModel model) {
	  ConstraintViolations violations = CustomerValidator.validate(model); if
	  (violations.isValid()) { return Mono.just(model); } else { return
	  Mono.error(new ValidationException(violations.violations())); } }
	 
	**/
	
}
