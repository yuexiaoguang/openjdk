// Platform-specific definitions for method handles.
// These definitions are inlined into class MethodHandles.

// Adapters
enum /* platform_dependent_constants */ {
  adapter_code_size = NOT_LP64(23000 DEBUG_ONLY(+ 40000)) LP64_ONLY(35000 DEBUG_ONLY(+ 50000))
};

// Additional helper methods for MethodHandles code generation:
public:
  static void load_klass_from_Class(MacroAssembler* _masm, Register klass_reg, Register temp_reg, Register temp2_reg);

  static void verify_klass(MacroAssembler* _masm,
                           Register obj_reg, SystemDictionary::WKID klass_id,
                           Register temp_reg, Register temp2_reg,
                           const char* error_message = "wrong klass") NOT_DEBUG_RETURN;

  static void verify_method_handle(MacroAssembler* _masm, Register mh_reg,
                                   Register temp_reg, Register temp2_reg) {
    verify_klass(_masm, mh_reg, SystemDictionary::WK_KLASS_ENUM_NAME(java_lang_invoke_MethodHandle),
                 temp_reg, temp2_reg,
                 "reference is a MH");
  }

  static void verify_ref_kind(MacroAssembler* _masm, int ref_kind, Register member_reg, Register temp) NOT_DEBUG_RETURN;

  // Similar to InterpreterMacroAssembler::jump_from_interpreted.
  // Takes care of special dispatch from single stepping too.
  static void jump_from_method_handle(MacroAssembler* _masm, Register method,
                                      Register temp, Register temp2,
                                      bool for_compiler_entry);

  static void jump_to_lambda_form(MacroAssembler* _masm,
                                  Register recv, Register method_temp,
                                  Register temp2, Register temp3,
                                  bool for_compiler_entry);
