package com.egmvdev.agenda.data.model;

import java.io.Serializable;

public class empleado implements Serializable{
    private String statusDesc;

    private Integer statusID;

    private Datos datos;

    public String getStatusDesc() {
        return this.statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getStatusID() {
        return this.statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public Datos getDatos() {
        return this.datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public static class Datos implements Serializable {
        private String empID;

        private Puesto puesto;

        private String apMaterno;

        private Gerencia gerencia;

        private String nombre;

        private String apPaterno;

        private Info info;

        public String getEmpID() {
            return this.empID;
        }

        public void setEmpID(String empID) {
            this.empID = empID;
        }

        public Puesto getPuesto() {
            return this.puesto;
        }

        public void setPuesto(Puesto puesto) {
            this.puesto = puesto;
        }

        public String getApMaterno() {
            return this.apMaterno;
        }

        public void setApMaterno(String apMaterno) {
            this.apMaterno = apMaterno;
        }

        public Gerencia getGerencia() {
            return this.gerencia;
        }

        public void setGerencia(Gerencia gerencia) {
            this.gerencia = gerencia;
        }

        public String getNombre() {
            return this.nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApPaterno() {
            return this.apPaterno;
        }

        public void setApPaterno(String apPaterno) {
            this.apPaterno = apPaterno;
        }

        public Info getInfo() {
            return this.info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public static class Puesto implements Serializable {
            private String puestoDesc;

            private Integer puestoID;

            public String getPuestoDesc() {
                return this.puestoDesc;
            }

            public void setPuestoDesc(String puestoDesc) {
                this.puestoDesc = puestoDesc;
            }

            public Integer getPuestoID() {
                return this.puestoID;
            }

            public void setPuestoID(Integer puestoID) {
                this.puestoID = puestoID;
            }
        }

        public static class Gerencia implements Serializable {
            private Integer gerenciaID;

            private String gerenciaDesc;

            public Integer getGerenciaID() {
                return this.gerenciaID;
            }

            public void setGerenciaID(Integer gerenciaID) {
                this.gerenciaID = gerenciaID;
            }

            public String getGerenciaDesc() {
                return this.gerenciaDesc;
            }

            public void setGerenciaDesc(String gerenciaDesc) {
                this.gerenciaDesc = gerenciaDesc;
            }
        }

        public static class Info implements Serializable {
            private Integer sexoID;

            private String sexoDesc;

            private Integer edad;

            public Integer getSexoID() {
                return this.sexoID;
            }

            public void setSexoID(Integer sexoID) {
                this.sexoID = sexoID;
            }

            public String getSexoDesc() {
                return this.sexoDesc;
            }

            public void setSexoDesc(String sexoDesc) {
                this.sexoDesc = sexoDesc;
            }

            public Integer getEdad() {
                return this.edad;
            }

            public void setEdad(Integer edad) {
                this.edad = edad;
            }
        }
    }
}
