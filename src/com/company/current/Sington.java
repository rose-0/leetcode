package com.company.current;


    public  class Sington{
        private  static Sington uniqueInstance;
        private Sington(){

        }
        public static Sington getUniqueInstance(){
            if(uniqueInstance==null){
                synchronized (Sington.class){
                    if(uniqueInstance==null){
                        uniqueInstance=new Sington();
                    }
                }
            }
            return uniqueInstance;
        }

    }

