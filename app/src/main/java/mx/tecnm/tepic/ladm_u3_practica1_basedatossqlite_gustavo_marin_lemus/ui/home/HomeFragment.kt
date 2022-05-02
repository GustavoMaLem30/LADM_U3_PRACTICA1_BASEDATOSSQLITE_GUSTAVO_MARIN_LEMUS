package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.Area
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.arregloArea
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.idInfo
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.databinding.ActivityMainBinding
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.databinding.FragmentHomeBinding
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.MainActivity
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.Subdepartamento


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val auxiliar =  Area(requireContext())
        var auxiliar2 =  Subdepartamento(requireContext())
        binding.listaDeDatos.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,auxiliar.mostrarTodos())
        binding.btnAgregar.setOnClickListener {
            auxiliar.descripcion = binding.editDesc.text.toString()
            auxiliar.division = binding.editDivi.text.toString()
            auxiliar.cantidadEmpleados = binding.editEmpleado.text.toString()
            if(auxiliar.insertar()){
                Toast.makeText(requireContext(),"¡SE AGREGO DE MANERA CORRECTA!",Toast.LENGTH_LONG).show()
                binding.editDesc.setText("")
                binding.editDivi.setText("")
                binding.editEmpleado.setText("")
            }
            binding.listaDeDatos.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,auxiliar.mostrarTodos())
        }
        binding.listaDeDatos.setOnItemClickListener { adapterView, view, i, l ->
                idInfo = arregloArea.get(i).idArea
                binding.editDesc.setText(arregloArea.get(i).descripcion)
                binding.editDivi.setText (arregloArea.get(i).division)
                binding.editEmpleado.setText(arregloArea.get(i).cantidadEmpleados)
        }
        binding.btnActualiza.setOnClickListener {
            if(idInfo == -1) {
                Toast.makeText(
                    requireContext(),
                    "¡ELIJA UN REGISTRO A MODIFICAR DEL LISTADO!",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                auxiliar.idArea = idInfo
                auxiliar.descripcion = binding.editDesc.text.toString()
                auxiliar.division = binding.editDivi.text.toString()
                auxiliar.cantidadEmpleados = binding.editEmpleado.text.toString()
                if (auxiliar.actualizar()) {
                    Toast.makeText(
                        requireContext(),
                        "¡ACTUALIZACIÓN COMPLETADA!",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.listaDeDatos.adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_expandable_list_item_1,
                        auxiliar.mostrarTodos()
                    )
                    binding.editDesc.setText("")
                    binding.editDivi.setText("")
                    binding.editEmpleado.setText("")
                    idInfo = -1
                }
            }
        }
        binding.btnBorrar.setOnClickListener {
            if(idInfo == -1) {
                Toast.makeText(
                    requireContext(),
                    "¡ELIJA UN REGISTRO A ELIMINAR DEL LISTADO!",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                auxiliar.idArea = idInfo
                if (auxiliar.eliminar()) {
                    Toast.makeText(requireContext(), "¡ELIMINACIÓN COMPLETADA!", Toast.LENGTH_LONG)
                        .show()
                    binding.listaDeDatos.adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_expandable_list_item_1,
                        auxiliar.mostrarTodos())
                        auxiliar2.eliminarPorArea(idInfo.toString())
                    binding.editDesc.setText("")
                    binding.editDivi.setText("")
                    binding.editEmpleado.setText("")
                    idInfo = -1
                }
            }
        }
        binding.btnBuscar.setOnClickListener {
            if(binding.editDesc.text.toString().equals("")){
                if (binding.editDivi.text.toString().equals("")){
                    binding.listaDeDatos.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,auxiliar.mostrarTodos())
                    Toast.makeText(requireContext(), "MOSTRANDO TODOS LOS DATOS", Toast.LENGTH_LONG).show()
                }else{
                    binding.listaDeDatos.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1, auxiliar.mostrarAreaDiv(binding.editDivi.text.toString()))
                    Toast.makeText(requireContext(), "MOSTRANDO DATOS POR DIVISIÓN", Toast.LENGTH_LONG).show()
                }
            }else{
                binding.listaDeDatos.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1, auxiliar.mostrarAreaDesc(binding.editDesc.text.toString()))
                Toast.makeText(requireContext(), "MOSTRANDO DATOS POR DESCRIPCIÓN", Toast.LENGTH_LONG).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}