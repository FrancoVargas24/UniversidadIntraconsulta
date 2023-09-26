package ar.unlam.materia;

public class Profesor {
	
	private String nombre;
	private String apellido;
	private Integer dni;
	private static Integer id = 5000;
	private String fecha_nacimiento;

	public Profesor(Integer dni, String apellido, String nombre, String fecha_nacimiento) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.fecha_nacimiento=fecha_nacimiento;
		this.dni = dni;
		id++;
	} 
	
	public Integer getid() {
		return this.id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}


	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	
	
}
