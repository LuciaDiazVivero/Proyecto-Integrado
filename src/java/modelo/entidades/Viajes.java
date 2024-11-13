/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author msi
 */
@Entity
public class Viajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer idViaje; 
    @Column(name = "Nombre")
    private String nombre;  
    @JoinColumn(name = "idSalida", referencedColumnName = "ID")
    @ManyToOne
    private Lugar idSalida;
    @JoinColumn(name = "idDestino", referencedColumnName = "ID")
    @ManyToOne
    private Lugar idDestino;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;    
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;  
    @Column(name = "Costo")
    private Double costo;
    @OneToOne(optional = true)  // Indica que el alojamiento es opcional
    @JoinColumn(name = "idAlojamiento", referencedColumnName = "idAlojamiento")
    private Alojamiento alojamiento;
  

    public Integer getIdViaje() {
        return idViaje;
    }

    public void setId(Integer idViaje) {
        this.idViaje = idViaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lugar getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(Lugar idSalida) {
        this.idSalida = idSalida;
    }

    public Lugar getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Lugar idDestino) {
        this.idDestino = idDestino;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idViaje != null ? idViaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viajes)) {
            return false;
        }
        Viajes other = (Viajes) object;
        if ((this.idViaje == null && other.idViaje != null) || (this.idViaje != null && !this.idViaje.equals(other.idViaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Viajes[ id=" + idViaje + " ]";
    }
    
}
