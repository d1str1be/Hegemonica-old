//public void move(Province province) {
//        if (province.unitThere == null) { //если прова пуста, захватываем
//            this.province.unitThere = null;
//            province.unitThere = this;
//            this.province = province;
//            movementPoints--;
//        } else { //иначе атакуем прову, если там враг
//            if(this.homeProvince.owner == province.owner) //если там союзный юнит, отменяем действие
//                return;
//
//            attack(province.unitThere);//это случай когда в провинции есть вражеский юнит. производим атаку
//            if (this.health <= 0) {
//                destroy();
//            } else if (province.unitThere.health <= 0) {
//                province.unitThere.destroy();
//                this.province.unitThere = null;
//                province.unitThere = this;
//                this.province = province;
//            }
//        }
//        movementPoints--;
//    }